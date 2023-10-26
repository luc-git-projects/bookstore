package br.com.storm.bookstore.controller;

import br.com.storm.bookstore.dto.BookDto;
import br.com.storm.bookstore.facade.BookFacade;
import br.com.storm.bookstore.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books/v1")
public class BookController {

    private final BookFacade bookFacade;

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto save(@RequestBody BookDto bookDto){
        List<Long> idsAuthors = bookDto.getIdAuthors();
        Book book = new Book(bookDto);
        book = bookFacade.save(idsAuthors, book);
        bookDto = new BookDto(book);
        return bookDto;
    }
}
