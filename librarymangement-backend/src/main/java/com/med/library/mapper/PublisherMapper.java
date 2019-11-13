package com.med.library.mapper;

import com.med.library.dTo.PublisherDTO;
import com.med.library.entity.Publisher;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {

    PublisherDTO toDto(Publisher publisher);
    Publisher toEntity(PublisherDTO publisherDTO);
    @IterableMapping(qualifiedByName = "toDto")
    List<PublisherDTO> toDtos(List<Publisher> publishers);
}
