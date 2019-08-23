package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    // primary key - tells system to generate it their way
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    // any columns required?
    private String booktitle;
    private String isbn;
    private int copy;

    //many:many books:authors
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    // create base constructor

    public Book()
    {
    }


    // create constructor

    public Book(String booktitle, String isbn, int copy)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
    }


    // generate getters and setters


    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }
}
