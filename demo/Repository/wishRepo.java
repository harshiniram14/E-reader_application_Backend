package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Book;
import com.example.demo.Models.Wishlist;

@Repository
public interface wishRepo extends JpaRepository<Wishlist, Integer>{
	
	
}
