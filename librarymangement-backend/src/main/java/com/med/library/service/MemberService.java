package com.med.library.service;

import com.med.library.entity.Borrow;
import com.med.library.entity.Member;
import com.med.library.entity.Penalty;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public List<Member> findAll();
    public Optional<Member> findById(Long memberId);
    public Member save(Member member);
    public void deleteById(Long memberId);
    public void addBorrow(Member member, Borrow borrow);
    public void addPenalty(Member member, Penalty penalty);
}
