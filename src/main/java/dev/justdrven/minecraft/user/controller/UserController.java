package dev.justdrven.minecraft.user.controller;

import dev.justdrven.minecraft.user.exception.BadRequestBodyException;
import dev.justdrven.minecraft.user.exception.UserAlreadyExistException;
import dev.justdrven.minecraft.user.exception.UserNotFoundException;
import dev.justdrven.minecraft.user.orm.User;
import dev.justdrven.minecraft.user.orm.UserRequest;
import dev.justdrven.minecraft.user.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Optional<User> user = userRepository.findByUuid(uuid);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }


    @GetMapping("/nick/{nick}")
    public ResponseEntity<User> getUserByNick(@PathVariable("nick") String nick) {
        Optional<User> user = userRepository.findByNick(nick);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody(required = false) UserRequest userRequest) {
        if (userRequest.getNick() == null || userRequest.getUuid() == null) {
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

}
