package com.yunpznr.gabutan.controller.user;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.user.update.UpdateUserRequest;
import com.yunpznr.gabutan.model.user.update.UpdateUserResponse;
import com.yunpznr.gabutan.service.auth.AuthService;
import com.yunpznr.gabutan.service.user.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<GetUserResponse>> getUser(@PathVariable(name = "id") UUID id) {
        GetUserResponse user = userService.getUser(id);

        return ResponseEntity.status(200).body(
                WebResponse.<GetUserResponse>builder()
                        .statusCode(200)
                        .message("Success get data")
                        .data(user)
                        .build()
        );
    }

    @GetMapping(path = "/get/my-profile",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<GetUserResponse>> getProfile(Authentication authentication){
        UUID profileId = UUID.fromString(authentication.getName());
        GetUserResponse user = userService.getUser(profileId);

        return ResponseEntity.status(200).body(
                WebResponse.<GetUserResponse>builder()
                        .statusCode(200)
                        .message("Success get data")
                        .data(user)
                        .build()
        );
    }

    @DeleteMapping(path = "/delete",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<String>> deleteUser(Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(
                WebResponse.<String>builder()
                        .statusCode(200)
                        .message("Success delete user")
                        .build()
        );
    }

    @PutMapping(path = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<UpdateUserResponse>> updateUser(@RequestBody UpdateUserRequest user) {
        UpdateUserResponse update = userService.updateUser(user);
        return ResponseEntity.status(200).body(
                WebResponse.<UpdateUserResponse>builder()
                        .statusCode(200)
                        .message("Success update user")
                        .data(update)
                        .build()
        );
    }
}
