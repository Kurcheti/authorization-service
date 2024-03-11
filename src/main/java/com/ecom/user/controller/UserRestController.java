package com.ecom.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.product.service.UserService;
import com.ecom.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path="/addUser", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<User> addUser(@RequestPart("user") String userJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(userJson, User.class);
		return new ResponseEntity<>(userService.addUser(user, multipartFile),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/updateUser", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<User> updateUser(@RequestPart("user") String userJson, @RequestPart("file") MultipartFile multipartFile) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(userJson, User.class);
		return new ResponseEntity<>(userService.updateUser(user, multipartFile),HttpStatus.OK);
	}
	
	@PostMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping(path="/getUserById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	@GetMapping(path="/deleteUserById/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId){
		userService.deleteUserById(userId);
		return new ResponseEntity<>("User "+userId+" deleted Successfully",HttpStatus.OK);
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<User> login(@RequestParam("user") User user){
		return new ResponseEntity<>(userService.login(user.getEmail(), user.getPassword()),HttpStatus.OK);
	}
	
	
}
