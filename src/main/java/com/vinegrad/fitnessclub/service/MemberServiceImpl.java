package com.vinegrad.fitnessclub.service;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean registerMember(Member member) {
        if(memberRepository.findByEmail(member.getEmail()).isPresent()) {
            return false;
        } else {
            memberRepository.save(new Member(member.getName().toLowerCase(), member.getEmail().toLowerCase()));
            return true;
        }
    }

    @Override
    public boolean deleteMemberById(Long id) {
        if(memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Member> findMemberByName(String name) {
        return memberRepository.findByName(name.toLowerCase());
    }

    @Override
    public Member updateMemberEmail(Long id, String newEmail) {
        var memberOptional = memberRepository.findById(id);
        if(memberOptional.isPresent()) {
            Member member = new Member(memberOptional.get().getId(), memberOptional.get().getName(), newEmail.toLowerCase());
            memberRepository.save(member);
            return member;
        } else {
            return null;
        }
    }
}
