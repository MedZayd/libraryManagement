package com.med.library.service.impl;

import com.med.library.dTo.LibraryMemberDto;
import com.med.library.entity.Borrow;
import com.med.library.entity.LibraryMember;
import com.med.library.entity.Penalty;
import com.med.library.mapper.LibraryMemberMapper;
import com.med.library.repository.LibraryMemberRepository;
import com.med.library.restExceptionHandler.exception.HttpNotFoundException;
import com.med.library.service.LibraryMemberService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private LibraryMemberMapper mMapper = Mappers.getMapper(LibraryMemberMapper.class);
    private LibraryMemberRepository mRepo;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository) {
        this.mRepo = libraryMemberRepository;
    }

    @Override
    public List<LibraryMemberDto> findByEnabled(boolean enabled) {
        List<LibraryMember> members = mRepo.findByEnabled(enabled);
        return mMapper.toDtos(members);
    }

    @Override
    public LibraryMemberDto findById(long id) {
        LibraryMember member = validateId(id);
        return mMapper.toDto(member);
    }

    @Override
    public LibraryMemberDto save(long id, LibraryMemberDto memberDto) {
        return null;
    }

    @Override
    public LibraryMemberDto update(long id, LibraryMemberDto memberDto) {
        return null;
    }

    @Override
    public LibraryMemberDto delete(long id) {
        return null;
    }

    private LibraryMember validateId(long id) {
        Optional<LibraryMember> member = mRepo.findById(id);
        if(!member.isPresent()) throw new HttpNotFoundException("Library Member ID " + id + " not found.");
        return member.get();
    }
}
