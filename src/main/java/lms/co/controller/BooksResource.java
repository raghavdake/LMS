package lms.co.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;

import lms.co.model.Book;

public interface BooksResource {

    ResponseEntity<Book> getBookByBookId(String bookid);
    ResponseEntity<Book> addBook(Book book);
    ResponseEntity<List<Book>> getAllBooks();
    ResponseEntity<Void> deleteBook(String bookid);

}
