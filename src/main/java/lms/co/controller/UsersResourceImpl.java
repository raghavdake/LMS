package lms.co.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lms.co.model.Book;
import lms.co.model.User;
import lms.co.repository.BookRepository;
import lms.co.repository.UsersRepository;

@RestController
@RequestMapping("/api")
public class UsersResourceImpl implements UsersResource {

    @Autowired
    private UsersRepository usersRepo;
   
    @Autowired
    private BookRepository booksRepo;

	@Override
    @RequestMapping(value = "lendbook/{userid}/{bookid}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Lend Books To Users")
	public ResponseEntity<User> lendBooksToUsers(@PathVariable String  userid,@PathVariable String bookid) {
		List<User> users = usersRepo.getAllUsers();
		List<Book> books = booksRepo.getAllBooks();
		
		User borroweruser = new User();
		borroweruser.setUserid(userid);
		User userTemp = null;
		 int  bookCount = 1;
		for(int i = 0; i< users.size() ; i++)
		{
			 if(userid.equalsIgnoreCase(users.get(i).getUserid())){
				 bookCount = users.get(i).getLentBookCount();
				 userTemp= users.get(i);
				 usersRepo.removeUser(userid);
				 break;
			 }
		}
		
		if(null == booksRepo.getBook(bookid))
		{
			borroweruser.setName(userTemp.getName());
 			borroweruser.setAddress(userTemp.getAddress());
 			borroweruser.setLentBookCount(bookCount);
 			usersRepo.addUser(borroweruser);
			borroweruser.setStatus("Please check the requested book. No such book present in library.");
			return new ResponseEntity<>(borroweruser, HttpStatus.OK);
		}
		
		if(bookCount > 3)
		{
			borroweruser.setName(userTemp.getName());
 			borroweruser.setAddress(userTemp.getAddress());
 			borroweruser.setLentBookCount(bookCount);
 			usersRepo.addUser(borroweruser);
			borroweruser.setStatus("Max 3  books can borrowed by per user");
			return new ResponseEntity<>(borroweruser, HttpStatus.OK);
		}
		
		for(int i = 0; i< books.size() ; i++) {
			Book book = books.get(i);
			if(null != book && book.getBookId().equals(bookid)&& !book.getStatus().equals("Available"))
            	{
					borroweruser.setName(userTemp.getName());
		 			borroweruser.setAddress(userTemp.getAddress());
		 			borroweruser.setLentBookCount(bookCount);
		 			usersRepo.addUser(borroweruser);
					borroweruser.setStatus("This book is already lent out. Sorry.");
					return new ResponseEntity<>(borroweruser, HttpStatus.OK);
			}
			
			if(null != book && book.getBookId().equals(bookid)&& book.getStatus().equals("Available"))
            		  {
		    			book.setBorrower(userid);
		    			book.setStatus("Not Available");
		    			if(borroweruser.getBook1issue().equals("none")) {
		    				borroweruser.setBook1issue(book.getTitle());
		    				bookCount++;
		    				break;
		    				}
		    			else if(borroweruser.getBook2issue().equals("none")) {
		    				borroweruser.setBook2issue(book.getTitle());
		    				bookCount++;
		    				break;
		    			}
		    			else if(borroweruser.getBook3issue().equals("none")) {
		    				borroweruser.setBook3issue(book.getTitle());
		    				bookCount++;
		    				break;
		    			}
		    			else
		    				 borroweruser.setStatus("Max 3  books can borrowed by per user");
    				
    		}
			
		}
		
		for(int i = 0; i< users.size() ; i++)
		{
			 if(userid.equalsIgnoreCase(users.get(i).getUserid())){
             			 
             			borroweruser.setName(users.get(i).getName());
             			borroweruser.setAddress(users.get(i).getAddress());
             			borroweruser.setLentBookCount(bookCount);
             			usersRepo.addUser(borroweruser);
             			break;
             		}
        }
        return new ResponseEntity<>(borroweruser, HttpStatus.OK);
	}
	
	@Override
    @RequestMapping(value = "returnbook/{userid}/{bookid}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Return the books to Library")
	public ResponseEntity<List<Book>> returnBooksToLib(@PathVariable String  userid,@PathVariable String bookid) {
		List<User> users = usersRepo.getAllUsers();
		List<Book> books = booksRepo.getAllBooks();
		 
		User borroweruser = new User();
		books.forEach(book -> {
            if(book.getBookId().equals(bookid)
            		&& book.getStatus().equals("Not Available")
            		&& book.getBorrower().equals(userid))
            		{
            			borroweruser.setBook1return(book.getTitle());
            			book.setBorrower("none");
            			book.setStatus("Available");
            			 
            		}
           });
		
        users.forEach(user -> {
        	 if(user.getUserid().equals(userid))
             		{
        		 		if(user.getBook1issue().equals(borroweruser.getBook1issue()))
        		 			user.setBook1issue("none");
        		 		else if(user.getBook2issue().equals(borroweruser.getBook1issue()))
        		 			user.setBook2issue("none");
        		 		else
        		 		{
        		 		}
        		 			
             			 
             		}
        	 
        });
        return new ResponseEntity<>(books, HttpStatus.OK);
	}
    
}
