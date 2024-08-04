package librarymanagementsystem.service;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.entities.Book;
import librarymanagementsystem.mappers.BookMapper;
import librarymanagementsystem.repositories.BookRepo;
import librarymanagementsystem.utils.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;


    public List<BookDto> getAllBooks(){
        List <Book> books= bookRepo.findAll();
        return books.stream().map(bookMapper::toDto).toList();
    }

    //handle throw done
    //dto
    //mappers
    public BookDto getBook (Integer id){
        Book book = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException(id));
        return bookMapper.toDto(book);
    }

    public BookDto SaveBook (Book book){
        return bookMapper.toDto(bookRepo.save(book));
    }

    public Book updateBook (Integer id, Book bookDetails){
        return bookRepo.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            book.setPublicationYear(bookDetails.getPublicationYear());
            return bookRepo.save(book);
        }).orElseThrow(() -> new BookNotFoundException(id));
    }


    public void deleteBook (Integer id){
        bookRepo.deleteById(id);
    }
}
