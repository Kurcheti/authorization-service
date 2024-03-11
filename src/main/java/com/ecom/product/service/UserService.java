package com.ecom.product.service;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.product.repository.UserRepo;
import com.ecom.product.util.FileUtils;
import com.ecom.user.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user, MultipartFile multipartFile) throws IOException {
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"user_img");
		 user.setUserpic(fileCode+"-"+fileName);
		return userRepo.save(user);
	}
	
	public User updateUser(User user, MultipartFile multipartFile) throws IOException {
		userRepo.findById(user.getUserId()).orElseThrow(
				()->new RuntimeException("User not found with "+user.getUserId()));
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 String fileCode = FileUtils.saveFile(fileName, multipartFile,"user_img");
		 user.setUserpic(fileCode+"-"+fileName);
		return userRepo.save(user);
	}
	
	public User getUserById(Integer userId) {
		return userRepo.findById(userId).get();
	}
	
	public Page<User> getCategories(User product,Pageable pageable){
		return null;
	}
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	public void deleteUserById(Integer productId) {
		userRepo.deleteById(productId);
	}
	
	public User login(String email,String pwd) {
		return userRepo.findByEmailAndPassword(email, pwd);
	}

}
