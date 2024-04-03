package com.mukesh.expenseapp.Controller;

import com.mukesh.expenseapp.Exceptions.UserResponse;
import com.mukesh.expenseapp.Repository.Users;
import com.mukesh.expenseapp.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserServiceImpl userservice;
    @PostMapping("/login")
    public ResponseEntity<String> login(){

        return new ResponseEntity<>("Successfully logged in ...", HttpStatusCode.valueOf(200));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody Users user){

        Users u=userservice.createUser(user);
        UserResponse resp=new UserResponse();
        resp.setEmail(u.getEmail());
        resp.setId(u.getId());
        resp.setAge(u.getAge());
        resp.setName(u.getName());
        resp.setMesage("Successfully Created ...");

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

}
