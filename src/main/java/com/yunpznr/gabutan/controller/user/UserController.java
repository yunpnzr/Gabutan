package com.yunpznr.gabutan.controller.user;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.user.update.UpdateUserRequest;
import com.yunpznr.gabutan.model.user.update.UpdateUserResponse;
import com.yunpznr.gabutan.service.auth.AuthService;
import com.yunpznr.gabutan.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/get/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<GetUserResponse>> getUser(@PathVariable(name = "email") String email) {
        GetUserResponse user = userService.getUser(email);

        return ResponseEntity.status(200).body(
                WebResponse.<GetUserResponse>builder()
                        .statusCode(200)
                        .message("Success get data")
                        .data(user)
                        .build()
        );
    }

    @DeleteMapping(path = "/delete/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<String>> deleteUser(@PathVariable(value = "email") String email) {
        userService.deleteUser(email);
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
