package com.javaadr.renderapi.Controller;

import com.javaadr.renderapi.Controller.Validator.UserValidator;
import com.javaadr.renderapi.Entity.User;
import com.javaadr.renderapi.Entity.UserCredentials;
import com.javaadr.renderapi.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserValidator userValidator;
    private final UserService userService;
    @Autowired
    public UserController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
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

    @PostMapping("/{userId}/uploadImage")
    public ResponseEntity<String> uploadImage(
            @PathVariable UUID userId,
            @RequestParam("image") MultipartFile image) {

        try {
            User savedUser = userService.saveUserWithImage(userId, image);

            if (savedUser != null) {
                return ResponseEntity.ok("Image téléchargée avec succès. Chemin : " + savedUser.getProfil());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé pour l'ID : " + userId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'image.");
        }
    }
    @PutMapping("/userId")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User updateUser){
        userValidator.validate(updateUser);
        User user = userService.updateUser(userId, updateUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
