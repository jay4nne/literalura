package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private Integer downloadCount;
    private String authorName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;

    public Book(){}

    public Book(DataBook dataBook, Author author) {
        this.title = dataBook.title();
        this.author = author;
        this.language = dataBook.languages().isEmpty() ? "Desconhecido" : dataBook.languages().getFirst();
        this.downloadCount = dataBook.downloadCount();
        this.authorName = author.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return """ 
                ******** LIVRO ********
                Título: %s
                Nome do Autor: %s
                Linguagem: %s
                Downloads: %d
                ***********************
                """.formatted(title,
                author != null ? authorName : "Desconhecido",
                language,
                downloadCount);
    }
}


