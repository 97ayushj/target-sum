package com.example.targetsum.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseModel {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Integer> result;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
