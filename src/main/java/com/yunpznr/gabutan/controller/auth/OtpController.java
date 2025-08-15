package com.yunpznr.gabutan.controller.auth;

import com.yunpznr.gabutan.model.WebResponse;
import com.yunpznr.gabutan.model.user.otp.OtpResponse;
import com.yunpznr.gabutan.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/otp")
public class OtpController {
    @Autowired
    private OtpService otpService;

    @PostMapping(path = "/verify/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WebResponse<OtpResponse>> verifyOtp(@PathVariable(value = "email") String email,
                                                              @RequestBody String otp) {
        OtpResponse b = otpService.verifyOtp(email, otp);

        return ResponseEntity.status(200).body(
                WebResponse.<OtpResponse>builder()
                        .statusCode(200)
                        .message("Success verify otp")
                        .data(b)
                        .build()
        );
    }
}
