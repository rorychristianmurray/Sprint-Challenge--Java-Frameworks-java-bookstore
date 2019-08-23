package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// tell Spring this is a REST controller
// and ask Spring to set up a base endpoint
@RestController
@RequestMapping("/authors")
public class AuthorController
{
    // set-up endpoint logging
    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    // Autowire in the AuthorService class, giving us access to methods
    @Autowired
    AuthorService authorService;

    // map the GET /authors endpoint
    @ApiOperation(value = "returns all Authors", responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author(s) Found", responseContainer = "List", response = Author.class),
            @ApiResponse(code = 404, message = "Author(s) Not Found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error Finding Author", responseContainer = "List", response = Author.class)})
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(@PageableDefault(page = 0, size = 3) Pageable pageable, HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Author> allAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);

    }

}