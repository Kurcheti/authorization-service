package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.user.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
  public User findByEmail(String email); 
  public User findByEmailAndPassword(String email,String password);
}
