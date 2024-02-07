package com.javaadr.renderapi.integration;

import com.javaadr.renderapi.Entity.User;
import com.javaadr.renderapi.Repository.UserRepository;
import com.javaadr.renderapi.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserIt {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        User user1 = new User(id1, "John", "Rakoto", "rakoto.doe@example.com", "password", new Date(), "profil1", "address1", "gender1", "CIN1", "role1", null, null, 1);
        User user3 = new User(id1, "John", "Doe", "john.doe@example.com", "password", new Date(), "profil1", "address1", "gender1", "CIN1", "role1", null, null, 1);
        User user2 = new User(id2, "Jane", "Doe", "jane.doe@example.com", "password", new Date(), "profil2", "address2", "gender2", "CIN2", "role2", null, null, 2);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        UUID idNonTrouve = UUID.randomUUID();
        when(userRepository.findById(idNonTrouve)).thenReturn(Optional.empty());
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = userService.getUsers();

        assertThat(users).hasSize(2);
        assertThat(users.get(0).getFirstname()).isEqualTo("John");
        assertThat(users.get(1).getLastname()).isEqualTo("Doe");
    }
    @Test
    public void testFindById_UserExists() throws Exception {
        UUID user3Id = UUID.randomUUID();

        User user3 = new User(user3Id, "Jane", "Doe", "jane.doe@example.com", "password", new Date(), "profil2", "address2", "gender2", "CIN2", "role2", null, null, 2);
        when(userRepository.findById(user3Id)).thenReturn(Optional.of(user3));

        Optional<User> optionalUser = userService.findById(user3Id);

        assertThat(optionalUser).isPresent();

        User user = optionalUser.get();

        assertThat(user.getFirstname()).isEqualTo("Jane");
        assertThat(user.getLastname()).isEqualTo("Doe");
        assertThat(user.getEmail()).isEqualTo("jane.doe@example.com");
    }
}
