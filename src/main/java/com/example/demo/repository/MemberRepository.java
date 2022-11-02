package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member, Long>{
    @Query(value = "select * from member where email = :email", nativeQuery = true)
    Optional<Member> findByEmail(String email);
}
