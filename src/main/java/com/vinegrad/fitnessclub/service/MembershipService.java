package com.vinegrad.fitnessclub.service;

import com.vinegrad.fitnessclub.model.Member;
import com.vinegrad.fitnessclub.model.Membership;

public interface MembershipService {

    boolean addMembership(Membership membership, String email);
}
