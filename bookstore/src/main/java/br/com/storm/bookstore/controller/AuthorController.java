package br.com.storm.bookstore.controller;

import br.com.storm.bookstore.dto.AuthorDto;
import br.com.storm.bookstore.facade.AuthorFacade;
import br.com.storm.bookstore.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors/v1")
public class AuthorController {

    private final AuthorFacade authorFacade;

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/{id}")
    public AuthorDto findById(@PathVariable Long id){
        Author author = authorFacade.findById(id);
        AuthorDto authorDto = new AuthorDto(author);
        return authorDto;
    }
}
