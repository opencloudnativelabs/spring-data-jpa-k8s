package org.kube.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);
    BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Book book){
        logger.info("Book is being added :"+book);
        book = repository.save(book);
        logger.info("Book Saved:"+book);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }
}
