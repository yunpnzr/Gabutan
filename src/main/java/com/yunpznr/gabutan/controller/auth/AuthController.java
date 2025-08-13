package com.yunpznr.gabutan.controller.auth;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.RegisterRequest;
import com.yunpznr.gabutan.model.user.RegisterResponse;
import com.yunpznr.gabutan.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
}
