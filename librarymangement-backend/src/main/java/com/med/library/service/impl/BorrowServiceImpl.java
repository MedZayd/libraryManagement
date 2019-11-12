package com.med.library.service.impl;

import com.med.library.entity.Borrow;
import com.med.library.repository.BorrowRepository;
import com.med.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BorrowServiceImpl implements BorrowService {

    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    @Override
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    @Override
    public Optional<Borrow> findById(Long borrowId) {
        return borrowRepository.findById(borrowId);
    }

    @Override
    public Borrow save(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Override
    public void deleteById(Long borrowId) {
        borrowRepository.deleteById(borrowId);
    }
}
