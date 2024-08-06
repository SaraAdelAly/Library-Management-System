package librarymanagementsystem.controller;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.service.BookService;
import librarymanagementsystem.utils.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks () {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
        try {
            BookDto book = bookService.getBook(id);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
        BookDto savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Integer id, @RequestBody BookDto bookDetails) {
        try {
            BookDto updatedBook = bookService.updateBook(id, bookDetails);
            System.out.println(updatedBook.getAuthor());
            return ResponseEntity.ok(updatedBook);
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book deleted successfully");
        } catch (BookNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }
}
