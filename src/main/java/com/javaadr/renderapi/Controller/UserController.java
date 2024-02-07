package com.javaadr.renderapi.Controller;

import com.javaadr.renderapi.Entity.User;
import com.javaadr.renderapi.Entity.UserCredentials;
import com.javaadr.renderapi.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<User> findAll(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable UUID id){
        return userService.findById(id);
    }

    @PostMapping("/new")
    public User save(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials userCredentials) {
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();

        User authenticatedUser = userService.authenticate(email, password);

        if (authenticatedUser != null) {
            String token = Jwts.builder()
                    .setSubject(authenticatedUser.getId().toString())
                    .claim("id", authenticatedUser.getId())
                    .claim("email", authenticatedUser.getEmail())
                    .claim("role", authenticatedUser.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                    .signWith(SignatureAlgorithm.HS512, "45454545545455454546565465465464654656546546546546456456546546546565465465465465465454654654654654654654654654654654654654SecretKey")
                    .compact();

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PutMapping("/userId")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User updateUser){
        User user = userService.updateUser(userId, updateUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
