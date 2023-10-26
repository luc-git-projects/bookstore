package br.com.storm.bookstore.repository;

import br.com.storm.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long> {

}
