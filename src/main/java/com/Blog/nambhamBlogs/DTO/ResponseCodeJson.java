package com.Blog.nambhamBlogs.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCodeJson {
    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private int code;

    public ResponseCodeJson(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

}
