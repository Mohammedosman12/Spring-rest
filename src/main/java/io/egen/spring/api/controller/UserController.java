package io.egen.spring.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.spring.api.constant.URI;
import io.egen.spring.api.entity.User;
import io.egen.spring.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value=URI.USERS)
@Api(tags="Users")
public class UserController {
	
	UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Find All Users",notes="Returns a list of users in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value=URI.ID)
	@ApiOperation(value="Find User by Id",notes="Returns a user by id if it exist")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User findOne(@PathVariable("id") String userId){
		return userService.findById(userId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create user",notes="Creates a new user in the app with unique email")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Bad Request"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User create(@RequestBody User user){
		return userService.create(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value=URI.ID)
	@ApiOperation(value="Updates user",notes="Updates an existing user")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public User update(@PathVariable("id") String userId,@RequestBody User user){
		return userService.update(userId, user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=URI.ID)
	@ApiOperation(value="Delete user",notes="Deletes an existing user")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=404,message="Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public void delete(@PathVariable("id") String userId){
		userService.delete(userId);
	}
}
