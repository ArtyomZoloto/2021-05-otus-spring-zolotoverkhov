package ru.otus.homework05jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework05jdbc.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @ShellMethod(value = "Save book. usage: insert `title` `author` `genre`", key = {"insert", "save", "create"})
    String insertBook(@ShellOption String title, @ShellOption String author, @ShellOption String genre) {
        return libraryService.insertBook(title, author, genre);
    }

    @ShellMethod(value = "Find a book by id. usage: find_id `id`", key = "find_id")
    String findBookById(@ShellOption String id) {
        return libraryService.getBookDescriptionById(Long.parseLong(id));
    }

    @ShellMethod(value = "Find a book by params. usage: find `title` `author` `genre`", key = "find")
    String findBookByTitleAndAuthorAndGenre(@ShellOption String title,
                                        @ShellOption String author,
                                        @ShellOption String genre) {
        return libraryService.findByTitleAndAuthorAndGenre(title, author, genre);
    }

    @ShellMethod(value = "Update book info. usage: update `id` `title` `author` `genre`", key = {"u","update"})
    @ShellMethodAvailability(value = "isBookExists")
    String updateBook(String id, String title, String author, String genre) {
        return libraryService.updateById(Long.parseLong(id), title, author, genre);
    }

    @ShellMethod(value = "Delete book by id. usage: delete `id`", key = {"d","delete"})
    String deleteBook(String id) {
        return libraryService.deleteById(Long.parseLong(id));
    }
}
