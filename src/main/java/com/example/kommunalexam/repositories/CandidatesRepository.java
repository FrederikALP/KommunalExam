package com.example.kommunalexam.repositories;

import com.example.kommunalexam.models.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatesRepository extends JpaRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM candidates WHERE party_id = ?;", nativeQuery = true)
    List<Candidate> findCandidatesByPartyId(long partyId);
}