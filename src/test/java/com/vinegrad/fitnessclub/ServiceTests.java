package com.vinegrad.fitnessclub;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.repository.MemberRepository;
import com.vinegrad.fitnessclub.service.MemberService;
import static org.junit.Assert.*;

import com.vinegrad.fitnessclub.service.MemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    private Member member;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Before
    public void setup() {
        member = new Member(1L, "Jake", "jake@email.com");
    }

    @Test
    public void test_findByName_returnsCorrectMember() {
        when(memberRepository.findByName("jake")).thenReturn(List.of(member));

        var expected = List.of(member);
        var actual = memberService.findMemberByName("Jake");

        assertEquals(expected, actual);

        verify(memberRepository, times(1)).findByName("jake");
    }

    @Test
    public void test_AddNewMember_returnsTrue() {
        member = new Member("jake", "jake@email.com");
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.empty());

        boolean actual = memberService.registerMember(member);

        assertTrue(actual);


        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void test_AddNewMember_returnsFalseWhenAlreadyExists() {
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.of(member));

        boolean result = memberService.registerMember(member);

        assertFalse(result);

        verify(memberRepository, times(0)).save(member);
    }

    @Test
    public void test_deleteMember_returnsTrue() {
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        Member actual = memberRepository.findById(member.getId()).get();

        boolean result = memberService.deleteMemberById(member.getId());

        assertTrue(result);

        assertEquals(member, actual);

        verify(memberRepository, times(1)).deleteById(member.getId());

    }

    @Test
    public void test_deleteMemberById_DoesNothing_WhenNoIDExists() {
        when(memberRepository.findById(5L)).thenReturn(Optional.empty());

        boolean result = memberService.deleteMemberById(5L);

        assertFalse(result);

        verify(memberRepository, times(0)).deleteById(5L);
    }

    @Test
    public void test_updateMemberEmail_returnsCorrectResult() {
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        Member expected = new Member(member.getId(), member.getName(), "newemail@email.com");
        Member actual = memberService.updateMemberEmail(member.getId(), "newemail@email.com");

        assertEquals(expected, actual);

        verify(memberRepository, times(1)).save(expected);
    }

    @Test
    public void test_updareEmail_doesNothing_whenNoIDExists() {
        when(memberRepository.findById(member.getId())).thenReturn(Optional.empty());

        Member actual = memberService.updateMemberEmail(member.getId(), "email");

        assertNull(actual);

        verify(memberRepository, times(0)).save(member);
    }

}
