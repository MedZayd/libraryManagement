package com.med.library.service;

import com.med.library.dTo.LibraryMemberDto;
import com.med.library.entity.Borrow;
import com.med.library.entity.LibraryMember;
import com.med.library.entity.Penalty;

import java.util.List;
import java.util.Optional;

public interface LibraryMemberService {

    List<LibraryMemberDto> findByEnabled(boolean enabled);
    LibraryMemberDto findById(long id);
    LibraryMemberDto save(long id, LibraryMemberDto memberDto);
    LibraryMemberDto update(long id, LibraryMemberDto memberDto);
    LibraryMemberDto delete(long id);

    /*public List<LibraryMember> findAll();
    public Optional<LibraryMember> findById(Long memberId);
    public LibraryMember save(LibraryMember libraryMember);
    public void deleteById(Long memberId);
    public void addBorrow(LibraryMember libraryMember, Borrow borrow);
    public void addPenalty(LibraryMember libraryMember, Penalty penalty);*/
}
