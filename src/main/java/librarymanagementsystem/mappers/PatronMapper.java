package librarymanagementsystem.mappers;

import librarymanagementsystem.dtos.BookDto;
import librarymanagementsystem.dtos.CreatedPatron;
import librarymanagementsystem.dtos.PatronDto;
import librarymanagementsystem.entities.Book;
import librarymanagementsystem.entities.Patron;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatronMapper {
    Patron toEntity(PatronDto patronDto);
    PatronDto toDto(Patron patron);

    Patron toEntityFromCreatedPatron (CreatedPatron createdPatron);
    CreatedPatron toCreatedPatron(Patron patron);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patron partialUpdate(PatronDto patronDto, @MappingTarget Patron patron);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patron partialUpdate(CreatedPatron patronDto, @MappingTarget Patron patron);

}
