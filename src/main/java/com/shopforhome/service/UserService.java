package com.shopforhome.service;

import java.util.List;
import java.util.Optional;

import com.shopforhome.entity.User;


public interface UserService {
	
	User saveUser(User user);
	List<User> fetchUsers();
	void delete(User user);
	User update(User user);
	Optional<User> get(Long id);
	User get(String userName);

}
