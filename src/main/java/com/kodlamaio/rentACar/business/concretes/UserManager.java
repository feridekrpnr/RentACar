package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.GetAllUsersResponse;
import com.kodlamaio.rentACar.business.responses.users.ReadUserResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService {

	@Autowired
	private ModelMapperService mapper;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCheckService userCheckService;

	@Override
	public Result add(CreateUserRequest createUserRequest) {

		checkIfUserExistTcNo(createUserRequest.getTcNo());
		User user = this.mapper.forRequest().map(createUserRequest, User.class);
		if (userCheckService.CheckIfRealPerson(user)) {
			this.userRepository.save(user);
			return new SuccessResult("USER.ADDED");
		} else {
			throw new BusinessException("USER NOT ADDED");
		}

	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		User user = this.mapper.forRequest().map(deleteUserRequest, User.class);
		this.userRepository.delete(user);
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.mapper.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.UPDATED");
	}

	@Override
	public DataResult<User> getById(ReadUserResponse readUserResponse) {
		User item = this.mapper.forRequest().map(readUserResponse, User.class);
		item = userRepository.findById(readUserResponse.getId());
		return new SuccessDataResult<User>(item);
	}

	@Override
	public DataResult<List<GetAllUsersResponse>> getAll() {
		List<User> users = this.userRepository.findAll();
		List<GetAllUsersResponse> response = users.stream()
				.map(user -> this.mapper.forResponse().map(user, GetAllUsersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllUsersResponse>>(response);
	}

	private void checkIfUserExistTcNo(String tcNo) {
		User currentUser = this.userRepository.findByTcNo(tcNo);
		if (currentUser != null) {
			throw new BusinessException("USER.EXİST");
		}
	}

	@Override
	public DataResult<List<GetAllUsersResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<User> users = this.userRepository.findAll(pageable).getContent();
		List<GetAllUsersResponse> response = users.stream()
				.map(user -> this.mapper.forResponse().map(user, GetAllUsersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllUsersResponse>>(response);

	}

}
