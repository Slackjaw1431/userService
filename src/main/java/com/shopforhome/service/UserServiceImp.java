package com.shopforhome.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopforhome.entity.Role;
import com.shopforhome.entity.User;
import com.shopforhome.repo.RoleRepository;
import com.shopforhome.repo.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void initRoleAndUser() {

		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleRepo.save(adminRole);

		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleRepo.save(userRole);

		User adminUser = new User();
		adminUser.setUserName("admin");
		adminUser.setUserPassword(getEncodedPassword("admin"));
		adminUser.setUserFirstName("admin");
		adminUser.setUserLastName("admin");
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		System.out.println("Adding admin user");
		userRepo.save(adminUser);

		User user = new User();
		user.setUserName("user");
		user.setUserPassword(getEncodedPassword("user"));
		user.setUserFirstName("bob");
		user.setUserLastName("sharma");
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		System.out.println("Adding user user");
		userRepo.save(user);
	}

	public User registerNewUser(User user) {
		Role role = roleRepo.findById("User").get();
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		user.setRole(userRoles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));

		return userRepo.save(user);
	}

	public void deleteUser(User user) {
		Set<Role> userRoles = new HashSet<>();
		user.setRole(userRoles);
		userRepo.delete(user);
	}

	public void updateUser(User user) {
		// check if user exists
		// if exists then save otherwise return error message
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleRepo.save(userRole);
		
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		
		userRepo.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<User> fetchUsers() {
		return (List<User>) userRepo.findAll();
	}

	@Override
	public void delete(User user) {
		if (userRepo.findByUserName(user.getUserName()) != null) {
			userRepo.delete(user);
		}
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(String userName) {
		if (userRepo.findByUserName(userName) != null) {
			return userRepo.findByUserName(userName);
		}
		return null;
	}

	@Override
	public Optional<User> get(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
