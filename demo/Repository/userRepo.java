package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Users;

@Repository
public interface userRepo extends JpaRepository<Users, Integer>{
	Users findByEmail(String email);

}
