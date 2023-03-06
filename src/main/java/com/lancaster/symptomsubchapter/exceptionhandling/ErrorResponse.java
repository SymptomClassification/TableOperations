package com.lancaster.symptomsubchapter.exceptionhandling;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> errors;

}
