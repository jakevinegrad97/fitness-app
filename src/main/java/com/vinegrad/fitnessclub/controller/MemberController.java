package com.vinegrad.fitnessclub.controller;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/addMember")
    public boolean addMember(@RequestBody Member member) {
        return memberService.registerMember(new Member(member.getName(), member.getEmail()));
    }

    @GetMapping("/deleteMember/{id}")
    public boolean deleteMember(@PathVariable Long id) {
        return memberService.deleteMemberById(id);
    }

    @GetMapping("findMembersByName/{name}")
    public List<Member> findMembersByName(@PathVariable String name) {
        return memberService.findMemberByName(name);
    }

    @PostMapping("updateMemberEmailById/{id}")
    public Member updateMemberEmailById(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMemberEmail(id, member.getEmail());
    }

}
