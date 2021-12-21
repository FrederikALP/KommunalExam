package com.example.kommunalexam.controllers;

import com.example.kommunalexam.DTO.response.CandidateCreateDTO;
import com.example.kommunalexam.models.Candidate;
import com.example.kommunalexam.repositories.CandidatesRepository;
import com.example.kommunalexam.repositories.PartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class Candidates {

    @Autowired
    CandidatesRepository candidates;

    @Autowired
    PartiesRepository parties;

    @GetMapping("/candidates")
    public Iterable<Candidate> getCandidates() {
        return candidates.findAll();
    }

    @GetMapping("/candidates/party/{partyId}")
    public List<Candidate> getCandidateByPartyId(@PathVariable Long partyId) {
        return candidates.findCandidatesByPartyId(partyId);
    }

    @PostMapping("/candidates/{partyId}")
    public CandidateCreateDTO createCandidate(@PathVariable Long partyId, @RequestBody Candidate newCandidate) {

        return parties.findById(partyId).map(party -> {
                    newCandidate.setId(null);
                    newCandidate.setParty(party);
                    Candidate createdCandidate = candidates.save(newCandidate);
                    return new CandidateCreateDTO(createdCandidate);
                }
        ).orElse(new CandidateCreateDTO("No Candidate found by: " + partyId));
    }

    @PostMapping("/candidates")
    public Candidate addCandidate(@RequestBody Candidate newCandidate) {
        newCandidate.setId(null);
        return candidates.save(newCandidate);
    }

    @PutMapping("/candidates/{id}")
    public String updateCandidateWithId(@PathVariable Long id, @RequestBody Candidate candidateToUpdateWith) {
        if (candidates.existsById(id)) {
            candidateToUpdateWith.setId(id);
            candidates.save(candidateToUpdateWith);
            return "Candidate was created";
        } else {
            return "Candidate not found";
        }
    }

    @PatchMapping("/candidates/{id}")
    public String patchCandidateById(@PathVariable Long id, @RequestBody Candidate candidateToUpdateWith) {
        return candidates.findById(id).map(foundCandidate -> {
            if (candidateToUpdateWith.getName() != null) foundCandidate.setName(candidateToUpdateWith.getName());
            if (candidateToUpdateWith.getParty() != null) foundCandidate.setParty(candidateToUpdateWith.getParty());

            candidates.save(foundCandidate);
            return "Candidate updated";
        }).orElse("Candidate not found");
    }

    @DeleteMapping("/candidates/{id}")
    public void deleteCandidateById(@PathVariable Long id) {
        candidates.deleteById(id);
    }
}