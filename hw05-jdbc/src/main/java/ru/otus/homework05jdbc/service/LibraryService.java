package ru.otus.homework05jdbc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework05jdbc.dao.author.AuthorDao;
import ru.otus.homework05jdbc.dao.book.BookDao;
import ru.otus.homework05jdbc.dao.genre.GenreDao;
import ru.otus.homework05jdbc.domain.Author;
import ru.otus.homework05jdbc.domain.Book;
import ru.otus.homework05jdbc.domain.Genre;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public String findByTitleAndAuthorAndGenre(String title, String authorName, String genreName) {
        Optional<Book> optionalBook = bookDao.findByTitleAndAuthorAndGenre(title, authorName, genreName);
        return optionalBook.map(Book::toString)
                .orElseGet(() -> "no book for params: " +
                        "title=" + title + ", " +
                        "author=" + authorName + ", " +
                        "genre=" + genreName);
    }

    public String insertBook(String title, String authorName, String genreName) {
        Author author = getAuthor(authorName);
        Genre genre = getGenre(genreName);
        Book book = new Book(null, title, author, genre);
        Long bookId = bookDao.insert(book);
        book.setId(bookId);
        return "inserted new book: " + book;
    }

    public String getBookDescriptionById(Long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        return optionalBook.map(Book::toString).orElseGet(() -> "no book for id = " + id);
    }

    public String updateById(Long id, String title, String authorName, String genreName) {
        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isEmpty()) {
            return "no book for id =" + id;
        }
        Book bookToUpdate = optionalBook.get();
        bookToUpdate.setTitle(title);
        bookToUpdate.setGenre(getGenre(genreName));
        bookToUpdate.setAuthor(getAuthor(authorName));
        boolean updateResult = bookDao.update(bookToUpdate);
        return updateResult ? "updated. new book params: " + bookToUpdate : "update failed";
    }

    public String deleteById(Long id) {
        boolean deleteResult = bookDao.deleteById(id);
        return deleteResult ? "deleted" : "fail to delete";
    }

    private Genre getGenre(String genreName) {
        Optional<Genre> optionalGenre = genreDao.findByName(genreName);
        Genre genre;
        if (optionalGenre.isEmpty()) {
            genre = new Genre(null, genreName);
            Long id = genreDao.insert(genre);
            genre.setId(id);
        } else {
            genre = optionalGenre.get();
        }
        return genre;
    }

    private Author getAuthor(String authorName) {
        Author author;
        Optional<Author> optionalAuthor = authorDao.findByName(authorName);
        if (optionalAuthor.isEmpty()) {
            author = new Author(null, authorName);
            Long id = authorDao.insert(author);
            author.setId(id);
        } else {
            author = optionalAuthor.get();
        }
        return author;
    }
}
