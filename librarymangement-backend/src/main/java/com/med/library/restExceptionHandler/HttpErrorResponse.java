package com.med.library.restExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @NoArgsConstructor @AllArgsConstructor
public class HttpErrorResponse<T> {
    private CustomHttpStatus status;
    private String message;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date date;
    private T content;
}
