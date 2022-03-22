package com.headcapital.documentspring.controller;

import com.headcapital.documentspring.entity.Book;
import com.headcapital.documentspring.exception.BookNotFoundException;
import com.headcapital.documentspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BookApiController implements BookApi {

  @Autowired
  private BookRepository repository;

  @Override
  public ResponseEntity<Book> findById(
      long id,
      String bookAuthorization) throws Exception {

    Book book = repository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Employee not found for this id :: " + id));

    return ResponseEntity.ok().body(book);
  }

  @Override
  public Collection<Book> findBooks() {
    return repository.findAll();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Book updateBook(@PathVariable("id") final String id, @RequestBody final Book book) {
    return book;
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Book patchBook(@PathVariable("id") final String id, @RequestBody final Book book) {
    return book;
  }

  @Override
  public ResponseEntity<Book> postBook(
      Book body,
      String bookAuthorization) throws Exception {
    return new ResponseEntity<Book>(repository.save(body), HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.HEAD, value = "/")
  @ResponseStatus(HttpStatus.OK)
  public Book headBook() {
    return new Book();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public long deleteBook(@PathVariable final long id) {
    return id;
  }
}