package br.com.storm.bookstore.facade;

import br.com.storm.bookstore.model.Author;
import br.com.storm.bookstore.model.AuthorHasBook;
import br.com.storm.bookstore.model.Book;
import br.com.storm.bookstore.service.AuthorService;
import br.com.storm.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookFacade {

    private final BookService bookService;
    private final AuthorService authorService;

    public Book save(List<Long> idsAuthors, Book book){
        List<Author> authorList = authorService.findByIds(idsAuthors);

        for (Author author: authorList) {
            AuthorHasBook authorHasBook = new AuthorHasBook();
            authorHasBook.setAuthor(author);
            book.addAuthorHasBooks(authorHasBook);
        }

        book = bookService.save(book);
        return book;
    }
}
