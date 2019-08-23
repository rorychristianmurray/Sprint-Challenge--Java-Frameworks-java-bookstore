package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.RoleRepository;
import com.lambdaschool.starthere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// tell Spring this is a service
@Service(value ="bookService")
public class BookServiceImpl implements BookService
{
    // Autowire in the UserRepository and the RoleRepository
    // to be able to use methods and assign to roles and users
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Autowire in BookRepository
    @Autowired
    private BookRepository bookRepository;

    // implement abstract methods

    // This will be our GET /books endpoint
    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Book findBookById(long id) throws ResourceNotFoundException
    {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }

    }

    @Override
    public Book save(Book book)
    {
        return null;
    }

    @Override
    public Book update(Book book, long id)
    {
        return null;
    }
}