package br.com.storm.bookstore.facade;

import br.com.storm.bookstore.model.Author;
import br.com.storm.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorFacade {

    private final AuthorService authorService;

    public Author findById(Long id){
        return authorService.findById(id);
    }

    public List<Author> findByIds(List<Long> ids){
        List<Author> authorList = authorService.findByIds(ids);
        return authorList;
    }
}
