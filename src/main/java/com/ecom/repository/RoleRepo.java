package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

}
