package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

  List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int birthYear, int deathYear);
}
