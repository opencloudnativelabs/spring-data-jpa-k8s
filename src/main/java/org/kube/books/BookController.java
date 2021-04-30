package org.kube.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity getAll(){
        logger.info("Requesting all Books");
        List<Book> books = repository.findAll();
        logger.info("Book Received:"+books);
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id){
        logger.info("Requesting Book for ID:"+id);
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()){
            logger.info("Book Received:"+book.get());
            return new ResponseEntity(book.get(), HttpStatus.OK);
        }else{
            logger.error("No book present with ID:"+id);
            return new ResponseEntity(book, HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id){
        logger.info("Going to Fetch book with ID:"+id);
        Optional<Book> book = repository.findById(id);
        if(book.isPresent()){
            logger.info("Book Received and would be deleted :"+book.get());
            repository.delete(book.get());
            return new ResponseEntity(book.get(), HttpStatus.OK);
        }else{
            logger.error("No book present with ID:"+id);
            return new ResponseEntity(book, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Book book){
        logger.info("Going to update book");
        Optional<Book> oldBook = repository.findById(book.getId());
        if(oldBook.isPresent()){
            logger.info("Book Received and would be updated :"+oldBook.get());
            repository.save(book);
            return new ResponseEntity(book, HttpStatus.OK);
        }else{
            logger.error("No book present with ID:"+book.getId());
            return new ResponseEntity(book, HttpStatus.NOT_FOUND);
        }
    }
}
