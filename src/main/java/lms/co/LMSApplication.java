package lms.co;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lms.co.model.Book;
import lms.co.model.User;
import lms.co.repository.BookRepository;
import lms.co.repository.UsersRepository;

@ComponentScan
@EnableAutoConfiguration
public class LMSApplication
{
    @Autowired
    private BookRepository booksRepo;
    
    @Autowired
    private UsersRepository usersRepo;


    public static void main( String[] args ){
        SpringApplication.run(LMSApplication.class, args);

    }

    @PostConstruct
    public void initApplication() throws IOException {
        booksRepo.addBook(new Book("111-1","Book1","James","Thriller","Available","none","none","none"));
        booksRepo.addBook(new Book("111-2","Book2","John","Comedy","Available","none","none","none"));
        booksRepo.addBook(new Book("111-3","Book3","Daniel","Sci-Fi","Available","none","none","none"));
        booksRepo.addBook(new Book("111-4","Book4","Martina","Romance","Available","none","none","none"));
        booksRepo.addBook(new Book("111-5","Book5","Mark","Romance","Available","none","none","none"));
        
        usersRepo.addUser(new User("111-1","USER1","DUBLIN","none","none","none","none",1));
        usersRepo.addUser(new User("111-2","USER2","CORK","none","none","none","none",1));
        usersRepo.addUser(new User("111-3","USER3","GALWAY","none","none","none","none",1));
        
        //usersRepo.addUser(new User("111-1","amit","Nainital","none","none","none","none"));
        //usersRepo.addUser(new User("111-2","amit","Delhi","none","none","none","none"));
        //usersRepo.addUser(new User("111-3","Harish","Lucknow","none","none","none","none"));
        
        
    }
}

