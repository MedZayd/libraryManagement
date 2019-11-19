package com.med.library.repository;

import com.med.library.entity.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {
    List<LibraryMember> findByEnabled(boolean enabled);
}
