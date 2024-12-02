package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.ApiConsumption;
import br.com.alura.literalura.service.ConvertsData;

import java.util.*;

public class Principal {
    private final Scanner input = new Scanner(System.in);
    private final ApiConsumption apiConsumption = new ApiConsumption();
    private final ConvertsData dataConverter = new ConvertsData();
    private final String ADDRESS = "https://gutendex.com/books/?search=";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();

    public Principal(BookRepository repository, AuthorRepository authorRepository) {
        this.bookRepository = repository;
        this.authorRepository = authorRepository;
    }

    public void displayMenu()
    {
        int option = -1;
        while (option != 7) {
            String menu = """
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                6 - Listar livros mais baixados
                7 - Sair                               
                """;

            System.out.println(menu);
            try {
                option = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Você não digitou uma opção válida");
                input.nextLine();
                continue;

        }
            switch (option) {
                case 1:
                    searchBook();
                    break;
                case 2:
                   listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorsAliveInAYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    listMostDownloadedBooks();
                    break;
                case 7:
                    System.out.println("Saindo da aplicação....");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    private DataBook getDataBooks() {
        System.out.println("Digite o nome do livro para busca: ");
        var bookName = input.nextLine();
        var json = apiConsumption.getData(ADDRESS + bookName.replace(" ", "+"));
        ApiResults results = dataConverter.getData(json, ApiResults.class);
        List<DataBook> books = results.result();
        return books.getFirst();
    }

    private void searchBook() {
        try {
            DataBook dataBook = getDataBooks();

            if (dataBook.author().isEmpty()) {
                System.out.println("Nenhum autor encontrado para o livro.");
                return;
            }

            AuthorData authorData = dataBook.author().getFirst();
            Author author = new Author(
                    authorData.name(),
                    Optional.ofNullable(authorData.birthYear()).orElse(0),
                    Optional.ofNullable(authorData.deathYear()).orElse(0)
            );


            Book book = new Book(dataBook, author);
            bookRepository.save(book);
            System.out.println("Livro salvo com sucesso: \n" + book);

        } catch (Exception e) {
            System.out.println("Erro ao buscar ou salvar o livro");
        }
    }

    private void listRegisteredBooks() {
        books = bookRepository.findAll();
        if(books.isEmpty()) {
            System.out.println("Não há livros registrados");
        } else {
                books.forEach(System.out::println);
            }
        }

    private void listRegisteredAuthors() {
        authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("Não há autores registrados");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void listAuthorsAliveInAYear() {
        System.out.println("Qual ano para busca de autores vivos? ");
        var year = 0;
        try {
            year = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Você não digitou um ano válido");
            input.nextLine();
            return;
        }

        authors = authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);

        if(authors.isEmpty()) {
            System.out.println("Não havia autores vivos na referida data");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void listBooksByLanguage() {
        System.out.println("""
                Digite o código do idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var languageOption = input.nextLine().toLowerCase();
        books = bookRepository.findByLanguage(languageOption);
        if (books.isEmpty()){
            System.out.println("Não há livros no idioma especificado");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void listMostDownloadedBooks() {
        books = bookRepository.findTop5ByOrderByDownloadCountDesc();
        if(books.isEmpty()){
            System.out.println("Não há livros cadastrados.");
        } else {
            System.out.println("Os livros mais baixados");
            books.forEach(b ->
                    System.out.printf("%n******** LIVRO ******** %nTítulo: %s%nNome do autor: %s%nDownloads: %d%n***********************%n",
                            b.getTitle(), b.getAuthor().getName(), b.getDownloadCount()));
        }
    }













}


