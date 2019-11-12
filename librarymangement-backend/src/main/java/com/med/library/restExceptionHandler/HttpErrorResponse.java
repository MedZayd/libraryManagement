package com.med.library.restExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class HttpErrorResponse {
    private int status;
    private String message;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date date;
}
