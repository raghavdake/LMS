package lms.co.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lms.co.model.Book;
import lms.co.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BooksResourceImpl implements BooksResource {

    @Autowired
    private BookRepository booksRepo;

    @Override
    @RequestMapping(value="/book/{bookid}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a book using its Id")
    public ResponseEntity<Book> getBookByBookId(@PathVariable String bookid) {
        Book result = booksRepo.getBook(bookid);
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            result.add(linkTo(methodOn(BooksResourceImpl.class).getBookByBookId(bookid)).withSelfRel());
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<Book>(result, httpStatus);

    }


    @Override
    @RequestMapping(value = "/book/add",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a book")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        booksRepo.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @RequestMapping(value="/books",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    @ApiOperation(value = "List all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = booksRepo.getAllBooks();
        books.forEach(book -> {
            book.removeLinks();
            book.add(linkTo(methodOn(BooksResourceImpl.class).getBookByBookId(book.getBookId())).withSelfRel());
        });
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/book/delete/{bookid}",
            method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a book")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookid) {
        HttpStatus httpStatus;
        if(booksRepo.removeBook(bookid)){
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(httpStatus);
    }

}
