package com.vinegrad.fitnessclub.service;

import com.vinegrad.fitnessclub.model.Member;

import java.util.List;

public interface MemberService {
    boolean registerMember(Member member);

    boolean deleteMemberById(Long id);

    List<Member> findMemberByName(String name);

    Member updateMemberEmail(Long id, String newEmail);
}
