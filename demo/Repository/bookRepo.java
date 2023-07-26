package com.example.demo.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Book;




@Repository
public interface bookRepo extends JpaRepository<Book, Integer>{

	Book findByTitleAndAuthor(String title, String author);
	Book findByTitle(String title);
	Optional<Book> findById(int  id);

}
