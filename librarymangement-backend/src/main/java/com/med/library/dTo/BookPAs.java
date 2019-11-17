package com.med.library.dTo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPAs {
    List<Long> authorsId;
    Long publisherId;
}
