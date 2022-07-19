package com.testcase.restapi.entity;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse {
    private String message;
    private int status;
    private Object data;
}
