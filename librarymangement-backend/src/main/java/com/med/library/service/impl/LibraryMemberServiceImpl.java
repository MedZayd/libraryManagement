package com.med.library.service.impl;

import com.med.library.entity.Borrow;
import com.med.library.entity.LibraryMember;
import com.med.library.entity.Penalty;
import com.med.library.repository.LibraryMemberRepository;
import com.med.library.service.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private LibraryMemberRepository libraryMemberRepository;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository) {
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public List<LibraryMember> findAll() {
        return libraryMemberRepository.findAll();
    }

    @Override
    public Optional<LibraryMember> findById(Long memberId) {
        return libraryMemberRepository.findById(memberId);
    }

    @Override
    public LibraryMember save(LibraryMember libraryMember) {
        return libraryMemberRepository.save(libraryMember);
    }

    @Override
    public void deleteById(Long memberId) {
        libraryMemberRepository.deleteById(memberId);
    }

    @Override
    public void addBorrow(LibraryMember libraryMember, Borrow borrow) {
        libraryMember.addBorrow(borrow);
        this.save(libraryMember);
    }

    @Override
    public void addPenalty(LibraryMember libraryMember, Penalty penalty) {
        libraryMember.addPenalty(penalty);
        this.save(libraryMember);
    }
}
