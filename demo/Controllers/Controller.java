package com.example.demo.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Models.Book;
import com.example.demo.Models.Users;
import com.example.demo.Models.Wishlist;
import com.example.demo.Repository.wishRepo;
import com.example.demo.Service.userService;
import com.example.demo.Service.userService.BookNotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/user")
public class Controller {
	@Autowired
	userService us;
	@Autowired
	wishRepo wishRep;
	//user
	@PostMapping("/Login")
	private String Login(@RequestBody Map<String, String> xLogin) {
	    String email = xLogin.get("email");
	    String password = xLogin.get("password");
	    String result = us.Login(email, password);
	    return result;
	}

    @PostMapping("/Signup")
    public String Signup(@RequestBody Users user) {
        return us.Signup(user);
    }
    @GetMapping("/Allusers")
    public List<Users> getAllUsers() {
        return us.getAllUsers();
    }
    
    //book
    //post
    @PostMapping("/postbook")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            Book addedBook = us.addData(book);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already exists");
        }
    }
    //get
    @GetMapping("/getbook")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = us.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @GetMapping("/getbook/{bookid}")
    public ResponseEntity<Book> getBookById(@PathVariable int bookid) {
        Optional<Book> book = us.getBookid(bookid);

        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    //put
    @PutMapping("/updatebook")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        try {
            us.updateBook(book);
            return ResponseEntity.ok("Book updated successfully.");
        } catch (userService.BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the book.");
        }
    }
    @PostMapping("/addtoWishlist")
    public String addtoWishlist(@RequestBody Wishlist book) {
    	wishRep.save(book);
    	return "Added to wishlist"; 
    }
    
    //delete
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody Map<String, String> requestMap) {
        String title = requestMap.get("title");
        String author = requestMap.get("author");

        try {
            us.deleteBookByTitleAndAuthor(title, author);
            return ResponseEntity.ok("Book deleted successfully.");
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the book.");
        }
    }
    @GetMapping("/view/wishlist")
    public List<Wishlist> getAllWishlist() {
        return wishRep.findAll();
    }
    
    @DeleteMapping("/wishdelete/{bookid}")
    public String deleteWishbook(@PathVariable int bookid)
	{
		wishRep.deleteById(bookid);
		return "Removed";
	}
}