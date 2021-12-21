package com.example.kommunalexam.DTO.response;

import com.example.kommunalexam.models.Candidate;

public class CandidateCreateDTO {

    public Candidate candidate;
    public boolean failed;
    public String errorMessage;

    // success case
    public CandidateCreateDTO(Candidate candidate) {
        this.candidate = candidate;
    }

    // failure case
    public CandidateCreateDTO(String errorMessage) {
        this.errorMessage = errorMessage;
        this.failed = true;
    }

}
