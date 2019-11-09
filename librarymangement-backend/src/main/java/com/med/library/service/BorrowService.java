package com.med.library.service;

import com.med.library.entity.Borrow;

import java.util.List;
import java.util.Optional;

public interface BorrowService {
    public List<Borrow> findAll();
    public Optional<Borrow> findById(Long borrowId);
    public Borrow save(Borrow borrow);
    public void deleteById(Long borrowId);
}
