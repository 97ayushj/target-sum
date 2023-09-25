package com.example.targetsum.controller;

import com.example.targetsum.exception.BadRequestException;
import com.example.targetsum.exception.NotFoundException;
import com.example.targetsum.model.RequestModel;
import com.example.targetsum.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@CrossOrigin
public class TargetSumController {


    @PostMapping(path = "sum/target")
    public ResponseEntity<ResponseModel> findPair(@RequestBody RequestModel request) throws BadRequestException, NotFoundException {
        ResponseModel responseModel = new ResponseModel();
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<Integer> numbers = request.getNumbers();

        if (numbers.size() < 2) {
            // throw exception
            throw new BadRequestException("Array of numbers is not valid");
        }
        if (numbers.get(0) > request.getTarget()) {
            //Bad req exception
            throw new BadRequestException("Array of numbers is not valid");
        }
        int s = 0;
        int e = request.getNumbers().size() - 1;
        while (s < e) {
            if (numbers.get(s) + numbers.get(e) == request.getTarget()) {
                ans.add(s);
                ans.add(e);
                break;
            } else if (numbers.get(s) + numbers.get(e) > request.getTarget()) {
                e--;
            } else if (numbers.get(s) + numbers.get(e) < request.getTarget()) {
                s++;
            }
        }

        if (ans.size() < 2) {
            // throw exeption
            throw new NotFoundException("Target sum is not posible using input numbers");
        }

        responseModel.setResult(ans);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}

