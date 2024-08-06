package library.books.librarymanagementsystem.service;


import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.entities.Book;
import librarymanagementsystem.mappers.BookMapper;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.service.BookService;
import librarymanagementsystem.utils.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        List<Book> books = List.of(new Book
                (1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10));
        when(bookRepo.findAll()).thenReturn(books);
        when(bookMapper.toDto(any(Book.class))).thenReturn(new BookDto(1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10));

        List<BookDto> bookDtos = bookService.getAllBooks();

        assertNotNull(bookDtos);
        assertEquals(1, bookDtos.size());
    }


    @Test
    void testGetBook() {
        Book book = new Book
                (1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10);
        when(bookRepo.findById(1)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(new BookDto(1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10));

        BookDto result = bookService.getBook(1);

        assertNotNull(result);
        assertEquals("To Kill a Mockingbird", result.getTitle());
    }

    @Test
    void testGetBookNotFound() {
        when(bookRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.getBook(1));
    }

    @Test
    void testSaveBook() {
        BookDto bookDto = new BookDto(1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10);
        Book book = new Book(1, "To Kill a Mockingbird", "Harper Lee", "9780060935467", LocalDate.of(1960, 7, 11), 10);

        when(bookMapper.toEntity(bookDto)).thenReturn(book);
        when(bookRepo.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        BookDto result = bookService.saveBook(bookDto);

        assertNotNull(result);
        assertEquals("To Kill a Mockingbird", result.getTitle());
    }


    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setBookId(1);
        when(bookRepo.findById(1)).thenReturn(Optional.of(book));
        bookService.deleteBook(1);
        verify(bookRepo, times(1)).delete(book);
    }

    @Test
    void testDeleteBookNotFound() {
        when(bookRepo.findById(1)).thenReturn(Optional.empty());
        bookService.deleteBook(1);
    }

}
