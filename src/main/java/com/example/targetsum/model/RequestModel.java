package com.example.targetsum.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RequestModel {

    private ArrayList<Integer> numbers;
    private int target;
}
