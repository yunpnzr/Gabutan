package com.yunpznr.gabutan.controller.auth;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.GetUserResponse;
import com.yunpznr.gabutan.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private AuthService authService;

    @GetMapping(path = "/get/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<GetUserResponse>> getUser(@PathVariable(name = "email") String email) {
        GetUserResponse user = authService.getUser(email);

        return ResponseEntity.status(200).body(
                WebResponse.<GetUserResponse>builder()
                        .statusCode(200)
                        .message("Success get data")
                        .data(user)
                        .build()
        );
    }
}
