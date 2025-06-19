package com.exos.services.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.exos.services.model.Candidate;
import com.exos.services.service.CandidateService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CandidateController.class)
public class CandidateControllerTest {

    @MockBean
    CandidateService candidateService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setLastName("Polusani");
        candidate.setFirstName("Kiran");
        List<Candidate> candidates = Arrays.asList(candidate);
        Mockito.when(candidateService.fetchCandidateList()).thenReturn(candidates);

        mockMvc.perform(get("/candidates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Kiran")));
    }

}
