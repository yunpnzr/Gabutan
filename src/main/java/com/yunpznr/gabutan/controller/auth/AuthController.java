package com.yunpznr.gabutan.controller.auth;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.login.LoginRequest;
import com.yunpznr.gabutan.model.user.login.LoginResponse;
import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;
import com.yunpznr.gabutan.service.auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping(path = "/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<RegisterResponse>> register(@RequestBody RegisterRequest request) {
        RegisterResponse register = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Success create user")
                        .data(register)
                        .build()
        );
    }

    @DeleteMapping(path = "/delete/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<RegisterResponse>> deleteUser(@PathVariable(value = "email") String email) {
        authService.deleteUser(email);
        return ResponseEntity.status(200).body(
                WebResponse.<RegisterResponse>builder()
                        .statusCode(200)
                        .message("Success delete user")
                        .build()
        );
    }

    @PostMapping(path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<WebResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse login = authService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success login")
                        .data(login)
                        .build()
        );
    }
}
