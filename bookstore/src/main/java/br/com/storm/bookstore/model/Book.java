package br.com.storm.bookstore.model;

import br.com.storm.bookstore.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import static br.com.storm.bookstore.util.DataConverter.string_dd_mm_yyyy_withSlashToLocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LAUNCH_DATE")
    private LocalDate launchDate;

    @Column(name = "PRICE")
    private BigDecimal price ;

    @Column(name = "ISBN")
    private String isbn;

    public Book(BookDto bookDto) {
        id = bookDto.getKey();
        title = bookDto.getTitle();
        launchDate = string_dd_mm_yyyy_withSlashToLocalDate(bookDto.getLaunchDate());
        price = bookDto.getPrice();
        isbn = bookDto.getIsbn();
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<AuthorHasBook> authorHasBooks = new LinkedHashSet<>();

    public void addAuthorHasBooks(AuthorHasBook authorHasBook){
        authorHasBooks.add(authorHasBook);
        authorHasBook.setBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
