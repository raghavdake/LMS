package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.Book;

@Component
public class BookRepository {
    HashMap<String, Book> books = new HashMap<>();

    public void addBook(Book book){

        books.put(book.getBookId(), book);
    }

    public Book getBook(String bookid){
        return books.get(bookid);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }

    public Boolean isBookAvailable(String bookid) {
        return books.containsKey(bookid);
    }

    public Boolean removeBook(String bookid) {
        if (books.containsKey(bookid)){
            books.remove(bookid);
            return true;
        }else{
            return false;
        }
    }
}
