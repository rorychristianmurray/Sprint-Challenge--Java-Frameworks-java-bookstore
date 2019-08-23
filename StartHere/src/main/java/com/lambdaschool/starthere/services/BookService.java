package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{

    // BookService will have several methods.
    // In short, we will be able to find all books,
    // find a book by its id, delete a book based
    // on id, save a new book to our database, and
    // update a book in our database based on id

    // when we find all books, we get back an array list of book type items
    List<Book> findAll(Pageable pageable);

    // when we search for a book, we get back a book type object
    Book findBookById(long id);

    // void because it doesn't return anything
    void delete(long id);

    // save new book type object
    Book save(Book book);

    Book update(Book book, long id);


}