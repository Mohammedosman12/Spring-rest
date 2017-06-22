package io.egen.spring.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.spring.api.entity.User;
import io.egen.spring.api.exception.BadRequestException;
import io.egen.spring.api.exception.NotFoundException;
import io.egen.spring.api.repository.UserRepository;
import io.egen.spring.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	UserRepository repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User findById(String userId) {
		
		User existing = repository.findById(userId);
		if(existing == null){
			throw new NotFoundException("User with id : "+userId+ "does not exist.");
		}
		return existing;
	}

	@Override
	@Transactional
	public User create(User user) {
		User existing = repository.findByEmail(user.getEmail());
		if(existing != null){
			throw new BadRequestException("User with id : "+user.getEmail()+ "already exist.");
		}
		return repository.create(user);
	}

	@Override
	@Transactional
	public User update(String userId, User user) {
		User existing = repository.findById(userId);
		if(existing == null){
			throw new NotFoundException("User with id : "+userId+ "does not exist.");
		}else{
		existing.setCity(user.getCity());
		existing.setFirstName(user.getFirstName());
		existing.setLastName(user.getLastName());
		existing.setEmail(user.getEmail());
		return repository.update(existing);
	}
	}
	@Override
	@Transactional
	public void delete(String userId) {
		User existing = repository.findById(userId);
		if(existing == null){
			throw new NotFoundException("User with id : "+userId+ "does not exist.");
		}
		repository.delete(existing);
	}

	
}
