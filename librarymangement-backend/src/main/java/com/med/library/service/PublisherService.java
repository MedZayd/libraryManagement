package com.med.library.service;

import com.med.library.dTo.PublisherDTO;
import com.med.library.entity.Book;
import com.med.library.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<PublisherDTO> findAllEnabled();
    PublisherDTO findById(long publisherId);
    PublisherDTO save(PublisherDTO publisherDTO);
    PublisherDTO update(PublisherDTO publisherDTO, long publisherId);
    PublisherDTO enable(boolean enable, long publisherId);
    void delete(Long publisherId);
}
