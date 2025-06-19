package com.exos.services.service;

import com.exos.services.model.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate saveCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate, Long id);
    List<Candidate> fetchCandidateList();
    Candidate findCandidateById(Long id);
    void deleteCandidate(Long id);
}
