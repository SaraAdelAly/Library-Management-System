package librarymanagementsystem.mappers;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.entities.Book;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

        Book toEntity(BookDto bookDto);
        BookDto toDto(Book book);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        Book partialUpdate(BookDto bookDto, @MappingTarget Book book);
}
