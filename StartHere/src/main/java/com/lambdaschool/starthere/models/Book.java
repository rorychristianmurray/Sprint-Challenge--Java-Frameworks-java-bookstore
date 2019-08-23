package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Add Swagger custom documentation
@ApiModel(value = "books", description = "Books with authors")
@Entity
@Table(name = "books")
public class Book extends Auditable
{
    // primary key - tells system to generate it their way
    @ApiModelProperty(name = "bookid", value = "primary key for books table", required = true, example = "42")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    // any columns required?
    @ApiModelProperty(name = "booktitle", value = "Title of book", required = true, example = "Threat Level Midnight")
    private String booktitle;

    @ApiModelProperty(name = "isbn", value = "ISBN number for book", required = true, example = "9788489367012")
    private String isbn;

    @ApiModelProperty(name = "copy", value = "copyright date for the book", required = true, example = "1977")
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
