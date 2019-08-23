package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// tell Spring this is a REST controller
// and ask Spring to set up a base endpoint
@RestController
@RequestMapping("/data")
public class DataController
{

    // set-up endpoint logging

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    // Autowire in the BookService class, giving us access to methods
    @Autowired
    BookService bookService;

    // Autowire in the AuthorService class, giving us access to methods
    @Autowired
    AuthorService authorService;

    // map the PUT /data/books/{id} endpoint
    // updates a books info (Title, Copyright, ISBN)
    // but does NOT have to assign authors to the books.
    @ApiOperation(value = "Updates a book by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Book Updated", response = void.class),
            @ApiResponse(code=404,message="Book Not Updated", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Updating Book", response = ErrorDetail.class)
    })
    @PutMapping(value = "/books/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@RequestBody Book book, @ApiParam(name = "bookid", required = true) @PathVariable long id)
    {
//        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.update(book, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // map the POST /data/books/{bookid}/authors/{authorid} endpoint
    // assigns a book already in the system (bookid) to an author already
    // in the system (authorid) (see how roles are handled for users)
    @ApiOperation(value = "Assigns a book by id to an author by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Book Assigned", response = void.class),
            @ApiResponse(code=404,message="Book Not Assigned", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Assigning Book", response = ErrorDetail.class)
    })
    @PostMapping(value = "/books/{bookid}/authors/{authorid}")
    // path variable tells Spring to add to our URL
    public ResponseEntity<?> assignBookToAuthor(HttpServletRequest request, @ApiParam(value = "bookid", example = "7") @PathVariable long bookid, @ApiParam(value = "authorid", example = "7") @PathVariable long authorid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get book and author by searching by id
        Book book = bookService.findBookById(bookid);
        Author author = authorService.findAuthorById(authorid);

        // add the author of the book to the book

        book.getAuthors().add(author);
        bookService.update(book, bookid);

        return new ResponseEntity<>(book, HttpStatus.OK);



    }

    // map the DELETE /data/books/{id} endpoint
    // deletes a book and the book author combinations
    // but does not delete the author records
    @ApiOperation(value = "Deletes a book by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Book deleted", response = void.class),
            @ApiResponse(code=404,message="Book Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Deleting Book", response = ErrorDetail.class)
    })
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

