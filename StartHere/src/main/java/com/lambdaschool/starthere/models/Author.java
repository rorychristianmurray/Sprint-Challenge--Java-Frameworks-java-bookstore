package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends Auditable
{
    // primary key - tells system to generate it their way
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String lastname;
    private String firstname;

    // many:many authors:books
    @ManyToMany
    @JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "authorid")}, inverseJoinColumns = {@JoinColumn(name = "bookid")})
    @JsonIgnoreProperties("authors")
    // authors will have a list of books they are associated with
    protected List<Book> books = new ArrayList<>();

    // create base constructor

    public Author()
    {
    }

    // create constructor

    public Author(String lastname, String firstname)
    {
        this.lastname = lastname;
        this.firstname = firstname;
    }


    // generate getters and setters


    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }

}
