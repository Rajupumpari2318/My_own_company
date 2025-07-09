package com.exos.services.controller;

import com.exos.services.model.Candidate;
import com.exos.services.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/candidates")
@Slf4j
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    // Save operation
    @PostMapping
    public List<Candidate> saveCandidate(@Valid @RequestBody List<Candidate> candidates) {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        List<Candidate> response = new ArrayList<>();
        candidates.forEach(dept -> response.add(candidateService.saveCandidate(dept)));
        return response;
    }

    // Read operation
    @GetMapping
    public List<Candidate> fetchCandidateList() {
        return candidateService.fetchCandidateList();
    }

    // Read By Id operation
    @GetMapping("/{id}")
    public Candidate fetchCandidateById(@PathVariable("id") Long candidateId) {
        return candidateService.findCandidateById(candidateId);
    }

    // Update operation
    @PutMapping("/{id}")
    public Candidate updateCandidate(@RequestBody Candidate candidate, @PathVariable("id") Long candidateId) {
        return candidateService.updateCandidate(candidate, candidateId);
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public String deleteCandidateById(@PathVariable("id") Long candidateId) {
        candidateService.deleteCandidate(candidateId);
        return "Deleted Successfully";
    }
}
