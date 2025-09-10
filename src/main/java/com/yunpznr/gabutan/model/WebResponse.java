package com.yunpznr.gabutan.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private int statusCode;
    private String message;
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
