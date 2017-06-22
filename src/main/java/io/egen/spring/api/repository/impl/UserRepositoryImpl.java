package io.egen.spring.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.spring.api.entity.User;
import io.egen.spring.api.repository.UserRepository;


@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail",User.class);
		query.setParameter("uEmail", email);
		List<User> users = query.getResultList();
		if(!(users.isEmpty())){
			return users.get(0);
		}else{
		return null;
		}
	}
	
	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createNamedQuery("User.findByAll",User.class);
		return query.getResultList();
	}

	@Override
	public User findById(String userId) {
		return em.find(User.class, userId);
	}

	@Override
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User update( User user) {
		
		return em.merge(user);
	}

	@Override
	public void delete(User user) {
		em.remove(user);
	}

}
