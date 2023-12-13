package com.shopforhome.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shopforhome.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
