package librarymanagementsystem.controller;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks (){
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}")
    public BookDto getBook (@PathVariable Integer id){
       return bookService.getBook(id);
    }

    @PostMapping
    public BookDto SaveBook (@RequestBody BookDto book){
        return bookService.SaveBook(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook (@PathVariable Integer id, @RequestBody BookDto bookDetails){
       return bookService.updateBook(id,bookDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook (@PathVariable Integer id){
        return bookService.deleteBook(id);
    }
}
