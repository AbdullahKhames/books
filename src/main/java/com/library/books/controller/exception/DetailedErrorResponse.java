package com.library.books.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailedErrorResponse {
    private String apiPath;
    private String errorMessage;
    private HttpStatus errorCode;
    private Date timestamp;
}
