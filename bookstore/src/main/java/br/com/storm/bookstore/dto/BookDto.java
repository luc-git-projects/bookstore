package br.com.storm.bookstore.dto;

import br.com.storm.bookstore.model.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static br.com.storm.bookstore.util.DataConverter.localDateToString_dd_mm_yyyy_withSlash;

@Getter
@Setter
public class BookDto implements Serializable {

    private Long key;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private List<String> authors;

    @Size(min = 1)
    private List<Long> idAuthors;

    @NotNull
    private String launchDate;

    @NotNull
    private BigDecimal price;

    @NotNull
    @NotEmpty
    private String isbn;

    public BookDto() {
        idAuthors = new ArrayList<>();
    }

    public BookDto(Book book){
        key = book.getId();
        title = book.getTitle();
        authors = returnAuthors(book);
        launchDate = localDateToString_dd_mm_yyyy_withSlash(book.getLaunchDate());
        price = book.getPrice();
        isbn = book.getIsbn();
        idAuthors = returnIdsAuthors(book);

    }

    private List<String> returnAuthors(Book book){
        List<String> authors;
        authors = book.getAuthorHasBooks().stream()
                .map(authorHasBook -> authorHasBook.getAuthor().getName())
                .toList();
        return authors;
    }

    private List<Long> returnIdsAuthors(Book book){
        List<Long> idsAuthors;
        idsAuthors = book.getAuthorHasBooks().stream().map(authorHasBook -> authorHasBook.getAuthor().getId()).toList();
        return idsAuthors;
    }
}
