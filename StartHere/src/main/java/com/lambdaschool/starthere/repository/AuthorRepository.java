package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

// endpoints will have paging and sorting
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long>
{
}