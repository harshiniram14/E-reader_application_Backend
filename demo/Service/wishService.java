package com.example.demo.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Book;
import com.example.demo.Models.Wishlist;
import com.example.demo.Repository.wishRepo;

@Service
public class wishService {
    @Autowired
    wishRepo wr;

   

    public List<Wishlist> getAllWishlistItems() {
        return wr.findAll();
    }
}
