package dev.justdrven.minecraft.user.controller;

import dev.justdrven.minecraft.user.exception.AccessDeniedException;
import dev.justdrven.minecraft.user.exception.BadRequestBodyException;
import dev.justdrven.minecraft.user.exception.UserAlreadyExistException;
import dev.justdrven.minecraft.user.exception.UserNotFoundException;
import dev.justdrven.minecraft.user.orm.User;
import dev.justdrven.minecraft.user.request.UserRequest;
import dev.justdrven.minecraft.user.repository.UserRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        value = "/api/v1/user",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<User> getUserByUUID(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(userRepository.findByUuid(uuid)
                .orElseThrow(UserNotFoundException::new));

    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUsers(@RequestHeader(value = "X-API-Key", required = false) String key) {
        if (!verifyAPIKey(key)) {
            throw new AccessDeniedException();
        }

        List<User> users = userRepository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("ETag", Base64.getEncoder().encodeToString(users.toString().getBytes()));

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/nick/{nick}")
    public ResponseEntity<User> getUserByNick(@PathVariable("nick") String nick) {
        return ResponseEntity.ok(userRepository.findByNick(nick)
                .orElseThrow(UserNotFoundException::new));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestHeader(value = "X-API-Key", required = false) String key, @RequestBody(required = false) UserRequest userRequest) {
        if (!verifyAPIKey(key)) {
            throw new AccessDeniedException();
        }

        if (userRequest == null || userRequest.getNick() == null || userRequest.getUuid() == null) {
            throw new BadRequestBodyException();
        }
        if (userRepository.existsByNick(userRequest.getNick())) {
            throw new UserAlreadyExistException();
        }

        User user = new User();

        user.setNick(userRequest.getNick());
        user.setUUID(userRequest.getUuid());

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/nick/{nick}")
    public ResponseEntity<User> deleteUserByNick(@PathVariable("nick") String nick) {
        User user = userRepository.findByNick(nick)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<User> deleteUserByUuid(@PathVariable("uuid") String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private boolean verifyAPIKey(String key) {
        if (key == null) {
            return false;
        }

        return System.getProperty("API_KEY", "test").equals(key);
    }

}
