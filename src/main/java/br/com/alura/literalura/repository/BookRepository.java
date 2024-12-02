package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);

    List<Book> findTop5ByOrderByDownloadCountDesc();
}
