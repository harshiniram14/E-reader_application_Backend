package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Book;
import com.example.demo.Models.Wishlist;
import com.example.demo.Service.wishService;

@CrossOrigin("*")
@RestController
public class wishController {
	@Autowired
	wishService ws;
	
	
	@GetMapping("/getWishlist")
	public ResponseEntity<List<Wishlist>> getWishlist() {
	    List<Wishlist> wishlistItems = ws.getAllWishlistItems();
	    return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
	}
}
