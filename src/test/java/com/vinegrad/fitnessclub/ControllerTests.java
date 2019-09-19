package com.vinegrad.fitnessclub;

import com.vinegrad.fitnessclub.controller.MemberController;
import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTests {

    private Member member;

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    @Before
    public void setup() {
        member = new Member(1L, "Jake", "jake@email.com");
    }

    @Test
    public void test_addMember_returnsTrue() {
        member = new Member("Jake", "jake@email.com");
        when(memberService.registerMember(member)).thenReturn(true);

        boolean actual = memberController.addMember(member);

        assertTrue(actual);

        verify(memberService, times(1)).registerMember(member);
    }

    @Test
    public void test_findMembersByName_returnsCorrectMember() {
        when(memberService.findMemberByName("jake")).thenReturn(List.of(member));

        List<Member> expected = List.of(member);
        List<Member> actual = memberController.findMembersByName("jake");

        assertEquals(expected, actual);

        verify(memberService, times(1)).findMemberByName("jake");
    }

    @Test
    public void test_deleteMemberById_returnsTrue() {
        when(memberService.deleteMemberById(1L)).thenReturn(true);

        boolean actual = memberController.deleteMember(1L);

        assertTrue(actual);

        verify(memberService, times(1)).deleteMemberById(1L);
    }

    @Test
    public void test_updateEmail_returnsNewMemberEmail() {
        when(memberService.updateMemberEmail(1L, "newemail@email.com")).thenReturn(new Member(member.getId(), member.getName(), "newemail@email.com"));

        Member expected = new Member(member.getId(), member.getName(), "newemail@email.com");
        Member actual = memberController.updateMemberEmailById(1L, new Member("", "newemail@email.com"));

        assertEquals(expected, actual);

        verify(memberService, times(1)).updateMemberEmail(1L, "newemail@email.com");
    }

}
