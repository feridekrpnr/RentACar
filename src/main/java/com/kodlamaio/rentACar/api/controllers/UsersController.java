package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.responses.users.ReadUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/add")
	public Result add( @RequestBody  CreateUserRequest createUserRequest) {
		return this.userService.add(createUserRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<User> getbyid(@RequestBody ReadUserResponse readUserResponse) {
		return this.userService.getById(readUserResponse);
	}
	
	@GetMapping("/getAll")
	
	public DataResult<List<GetAllUsersResponse>> getAll() {
		return this.userService.getAll();
	}

}
