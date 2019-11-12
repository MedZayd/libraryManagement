package com.med.library.service;

import com.med.library.entity.Borrow;
import com.med.library.entity.LibraryMember;
import com.med.library.entity.Penalty;

import java.util.List;
import java.util.Optional;

public interface LibraryMemberService {
    public List<LibraryMember> findAll();
    public Optional<LibraryMember> findById(Long memberId);
    public LibraryMember save(LibraryMember libraryMember);
    public void deleteById(Long memberId);
    public void addBorrow(LibraryMember libraryMember, Borrow borrow);
    public void addPenalty(LibraryMember libraryMember, Penalty penalty);
}
