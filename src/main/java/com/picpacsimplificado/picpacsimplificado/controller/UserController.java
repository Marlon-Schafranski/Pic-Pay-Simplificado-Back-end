package com.picpacsimplificado.picpacsimplificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.picpacsimplificado.picpacsimplificado.Services.UserService;
import com.picpacsimplificado.picpacsimplificado.domain.user.User;
import com.picpacsimplificado.picpacsimplificado.dtos.UserDTO;

@RestController()
@RequestMapping("/users")

public class UserController {

   @Autowired
   private UserService userService;

   @PostMapping

   public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
      User newUser = userService.createUser(user);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
   }

   @GetMapping
   public ResponseEntity<List<User>> getAllUsers() {
      List<User> users = this.userService.getAllUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
   }
}