package com.yunpznr.gabutan.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpznr.gabutan.model.WebResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException{

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        WebResponse<ErrorResponse> errorResponse = WebResponse.<ErrorResponse>builder()
                .statusCode(401)
                .message("Unauthorized")
                .data(null)
                .build();

        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}
