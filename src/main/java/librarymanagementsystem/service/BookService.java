package librarymanagementsystem.service;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.entities.Book;
import librarymanagementsystem.mappers.BookMapper;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.utils.exceptions.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;


    public List<BookDto> getAllBooks(){
        List <Book> books= bookRepo.findAll();
        System.out.println(books);
        return books.stream().map(bookMapper::toDto).toList();

    }

    public BookDto getBook (Integer id){
        Book book = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException(id));
        return bookMapper.toDto(book);
    }

    public Optional<Book> findBook (Integer id){
        return bookRepo.findById(id);
    }

    public BookDto saveBook (BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepo.save(book);
        return bookMapper.toDto(savedBook);
    }

    public void addBook(Book book){
        bookRepo.save(book);
    }

    public BookDto updateBook (Integer id, BookDto bookDetails){
        Book updatedBook= bookRepo.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setPublicationYear(bookDetails.getPublicationYear());
            book.setQuantity(bookDetails.getQuantity());
            return bookRepo.save(book);
        }).orElseThrow(() -> new BookNotFoundException(id));
        System.out.println(updatedBook.getTitle());
        return bookMapper.toDto(updatedBook);
    }



    public void deleteBook (Integer id){
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isEmpty()) {
            ResponseEntity.notFound().build();
            return;
        }
        Book book = bookOptional.get();
        bookRepo.delete(book);
    }
}
