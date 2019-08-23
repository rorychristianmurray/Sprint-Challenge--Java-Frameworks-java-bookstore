package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.RoleRepository;
import com.lambdaschool.starthere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// tell Spring this is a service
@Service(value ="authorService")
public class AuthorServiceImpl implements AuthorService
{
    // Autowire in the UserRepository and the RoleRepository
    // to be able to use methods and assign to roles and users
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Autowire in AuthorRepository
    @Autowired
    private AuthorRepository authorRepository;

    // implement abstract methods

    // This will be our GET /authors endpoint

    @Override
    public List<Author> findAll(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id) throws ResourceNotFoundException
    {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {

    }

    @Override
    public Author save(Author author)
    {
        return null;
    }

    @Override
    public Author update(Author author, long id)
    {
        return null;
    }
}
