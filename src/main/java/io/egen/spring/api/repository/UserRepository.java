package io.egen.spring.api.repository;

import java.util.List;

import io.egen.spring.api.entity.User;

public interface UserRepository {
	
	public List<User> findAll();
	
	public User findById(String userId);
	
	public User findByEmail(String email);
	
	public User create( User user);
	
	public User update(User user);
	
	public void delete(User user);

}
