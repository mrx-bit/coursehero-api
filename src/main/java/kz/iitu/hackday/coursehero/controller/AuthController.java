package kz.iitu.hackday.coursehero.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.iitu.hackday.coursehero.entity.User;
import kz.iitu.hackday.coursehero.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "Метод для регистрации пользователя в базе", response = ResponseEntity.class)
    public ResponseEntity<?> createUser(@ApiParam(value = "User object to provide user details to create in database", required = true)
                           @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }
}
