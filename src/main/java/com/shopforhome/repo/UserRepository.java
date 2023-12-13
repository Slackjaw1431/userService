package com.shopforhome.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByUserName(String userName);
	Page<User> findAll(Pageable pageable);

}
