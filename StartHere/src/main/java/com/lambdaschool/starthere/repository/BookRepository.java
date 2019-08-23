package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

// endpoints will have paging and sorting
public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
}

