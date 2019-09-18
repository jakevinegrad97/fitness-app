package com.vinegrad.fitnessclub.repository;

import com.vinegrad.fitnessclub.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    List<Member> findByName(String name);
}
