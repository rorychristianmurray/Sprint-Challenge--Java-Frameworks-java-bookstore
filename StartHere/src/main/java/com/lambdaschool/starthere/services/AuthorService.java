package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService
{

    // AuthorService will have several methods.
    // In short, we will be able to find all authors,
    // find an author by their id, delete an author based
    // on id, save a new author to our database, and
    // update an author in our database based on id

    // when we find all authors, we get back an array list of author type items
    List<Author> findAll(Pageable pageable);

    // when we search for an author, we get back an author type object
    Author findAuthorById(long id);

    // void because it doesn't return anything
    void delete(long id);

    // save new book type object
    Author save(Author author   );

    Author update(Author author, long id);


}