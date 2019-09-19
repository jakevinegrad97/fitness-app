package com.vinegrad.fitnessclub.controller;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.model.Membership;
import com.vinegrad.fitnessclub.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/membership")
@Controller
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/addMembership/{type}")
    @ResponseBody
    public boolean addMembership( @RequestBody Member member, @PathVariable String type) {
        return membershipService.addMembership(Membership.of(type, LocalDate.now()), member.getEmail());
    }
}
