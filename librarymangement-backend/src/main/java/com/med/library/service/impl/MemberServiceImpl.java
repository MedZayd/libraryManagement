package com.med.library.service.impl;

import com.med.library.entity.Borrow;
import com.med.library.entity.Member;
import com.med.library.entity.Penalty;
import com.med.library.repository.MemberRepository;
import com.med.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public void addBorrow(Member member, Borrow borrow) {
        member.addBorrow(borrow);
        this.save(member);
    }

    @Override
    public void addPenalty(Member member, Penalty penalty) {
        member.addPenalty(penalty);
        this.save(member);
    }
}
