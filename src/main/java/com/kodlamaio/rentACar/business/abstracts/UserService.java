package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.responses.users.ReadUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.User;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	DataResult<User>getById(ReadUserResponse readUserResponse);
	DataResult<List<GetAllUsersResponse>> getAll();
	DataResult<List<GetAllUsersResponse>> getAll(int pageNo,int pageSize);
	
	

}
