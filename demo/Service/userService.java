package com.example.demo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Models.Book;
import com.example.demo.Models.Users;
import com.example.demo.Models.Wishlist;
import com.example.demo.Repository.bookRepo;
import com.example.demo.Repository.userRepo;

@Service
public class userService {
	@Autowired
	userRepo ur;
	@Autowired
	bookRepo br;

	
	//Login Logic
		public String Login(String email, String password) {
			Users xuser = ur.findByEmail(email);
			if (xuser == null) {
				return "Invalid User";
			} else {
				if (xuser.getPassword().equals(password)) {
					return "Success";
				} else {
					return "Invalid Password";
				}
			}
		}

		//Signup Logic
		public String Signup(Users xuser) {
		    String email = xuser.getEmail();
		    Users authuser = ur.findByEmail(email);
		    if (authuser == null) {
		        ur.save(xuser);
		        return "User Added";
		    } else {
		        return "Existing Username";
		    }
		}
		//get all users
		public List<Users> getAllUsers() {
	        return ur.findAll();
	    }

		//book
		//post
		public Book addData(Book data) {
	        // Check if the book already exists based on title and author
	        Book existingBook = br.findByTitleAndAuthor(data.getTitle(), data.getAuthor());

	        if (existingBook == null) {
	            // The book does not exist, proceed to save it
	            return br.save(data);
	        } else {
	            // The book already exists, handle it appropriately (e.g., throw an exception)
	            throw new RuntimeException("Book already exists!");
	        }
	    }
		//get
		public List<Book> getAllBooks() {
	        return br.findAll();
	    }
		public Optional<Book> getBookid(int  bookid) {
	        return br.findById(bookid);
	    }
		
		//put
		 public static class BookNotFoundException extends RuntimeException {
		        public BookNotFoundException(String message) {
		            super(message);
		        }
		    }

		    public void updateBook(Book book) {
		        // Check if the book exists based on title and author
		        Book existingBook = br.findByTitleAndAuthor(book.getTitle(), book.getAuthor());

		        if (existingBook != null) {
		            // Update the book details
		            existingBook.setGenres(book.getGenres());
		            existingBook.setChapter(book.getChapter());
		            existingBook.setImage(book.getImage());
		            existingBook.setDescription(book.getDescription());

		            // Save the updated book
		            br.save(existingBook);
		        } else {
		            // Book with the given title and author not found
		            throw new BookNotFoundException("Book with the given title and author not found.");
		        }
		    }
		    
		    //delete
		    public void deleteBookByTitleAndAuthor(String title, String author) throws BookNotFoundException {
		        Book bookToDelete = br.findByTitleAndAuthor(title, author);
		        if (bookToDelete != null) {
		            br.delete(bookToDelete);
		        } else {
		            throw new BookNotFoundException("Book not found.");
		        }
		    }
		   
		    

		        

		    
		    
}