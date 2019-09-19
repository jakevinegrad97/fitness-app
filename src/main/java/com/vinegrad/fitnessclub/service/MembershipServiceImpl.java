package com.vinegrad.fitnessclub.service;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.model.Membership;
import com.vinegrad.fitnessclub.model.MembershipType;
import com.vinegrad.fitnessclub.repository.MemberRepository;
import com.vinegrad.fitnessclub.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public boolean addMembership(Membership membership, String email) {
        if (membership == null) {
            return false;
        }
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if(memberOptional.isEmpty()) {
            return false;
        } else {
            Member member = memberOptional.get();
            membership.setMember(member);
            member.addMembership(membership);
            System.out.println("Add membership...");
            membershipRepository.save(membership);
            System.out.println("Done");
            memberRepository.save(member);
            System.out.println("Member added");
            return true;
        }
    }
}
