package com.exos.services.service.impl;

import com.exos.services.repository.CandidateRepository;
import com.exos.services.service.CandidateService;
import com.exos.services.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate, Long id) {
        Optional<Candidate> opt = candidateRepository.findById(id);
        if(opt.isPresent()){
            Candidate act = opt.get();
            act.setCourse(candidate.getCourse());
            act.setEmailId(candidate.getEmailId());
            act.setFirstName(candidate.getFirstName());
            act.setGrade(candidate.getGrade());
            act.setLastName(candidate.getLastName());
            act.setMiddleName(candidate.getMiddleName());
            act.setMobileNumber(candidate.getMobileNumber());
            act.setStatus(candidate.getStatus());
            candidateRepository.save(act);
            return act;
        }
        return null;
    }

    @Override
    public List<Candidate> fetchCandidateList() {
        return (List<Candidate>) candidateRepository.findAll();
    }

    @Override
    public Candidate findCandidateById(Long id) {
        Optional<Candidate> opt = candidateRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
