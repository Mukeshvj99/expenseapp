package com.mukesh.expenseapp.Controller;

import com.mukesh.expenseapp.Exceptions.UserResponse;
import com.mukesh.expenseapp.Repository.Users;
import com.mukesh.expenseapp.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userservice;



    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){

        Users user=userservice.getUserById(id);
        UserResponse res=new UserResponse();
        res.setAge(user.getAge());
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setMesage("Successfully fetched the User...");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @GetMapping("/users/email/{email}")
   public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("email") String email){
        Users user=userservice.getUserByEmail(email);
        UserResponse res=new UserResponse();
        res.setAge(user.getAge());
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setMesage("Successfully fetched the User...");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
   }

   @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id,@RequestBody Users user){
         Users u=userservice.updateUsers(id,user);
         return new ResponseEntity<>(u.getName()+" is Successfully Updated",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable long id){


        Users user= userservice.deleteUsers(id);
        UserResponse resp=new UserResponse();
        resp.setEmail(user.getEmail());
        resp.setId(user.getId());
        resp.setAge(user.getAge());
        resp.setName(user.getName());
        resp.setMesage("Successfully Deleted  the User...");
         return new ResponseEntity<>(resp,HttpStatus.ACCEPTED);
    }

}
