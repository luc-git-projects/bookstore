package br.com.storm.bookstore.service;

import br.com.storm.bookstore.exception.EntityNotFoundException;
import br.com.storm.bookstore.model.Author;
import br.com.storm.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.lang.StringTemplate.STR;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findById(Long id){
        log.info("Find Author by id");
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(STR."Author with id = \{id} not found"));

        return author;
    }

    public List<Author> findByIds(List<Long> ids){
        List<Author> authorList = authorRepository.findByIdIn(ids);
        return authorList;
    }
}
