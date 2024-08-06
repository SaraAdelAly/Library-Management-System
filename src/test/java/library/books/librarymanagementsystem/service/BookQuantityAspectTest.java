package library.books.librarymanagementsystem.service;

import librarymanagementsystem.entities.Book;
import librarymanagementsystem.service.BookService;
import librarymanagementsystem.utils.advice.BookQuantityAspect;
import librarymanagementsystem.utils.exceptions.BookNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookQuantityAspectTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookQuantityAspect bookQuantityAspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Test
    void testAroundBorrowBook_Success() throws Throwable {
        // Arrange
        Book book = new Book();
        book.setBookId(1);
        book.setQuantity(1);

        when(bookService.findBook(1)).thenReturn(Optional.of(book));
        when(joinPoint.proceed()).thenReturn(null);

        // Act
        bookQuantityAspect.aroundBorrowBook(joinPoint, 1, 1);

        // Assert
        verify(bookService).findBook(1);
        verify(bookService).addBook(book);
        assertEquals(0, book.getQuantity());
    }

    @Test
    void testAroundBorrowBook_BookNotAvailable() throws Throwable {
        when(bookService.findBook(4)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookQuantityAspect.aroundBorrowBook(joinPoint, 4, 1));
    }
}
