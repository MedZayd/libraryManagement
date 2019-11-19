package com.med.library.mapper;

import com.med.library.dTo.LibraryMemberDto;
import com.med.library.entity.LibraryMember;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LibraryMemberMapper {
    LibraryMemberDto toDto(LibraryMember libraryMember);
    LibraryMember toEntity(LibraryMemberDto libraryMemberDto);
    @IterableMapping(qualifiedByName = "toDto")
    List<LibraryMemberDto> toDtos(List<LibraryMember> members);
}
