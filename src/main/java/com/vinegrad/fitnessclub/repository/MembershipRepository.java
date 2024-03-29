package com.vinegrad.fitnessclub.repository;

import com.vinegrad.fitnessclub.model.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
}
