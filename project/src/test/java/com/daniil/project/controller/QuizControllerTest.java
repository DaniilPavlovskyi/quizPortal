package com.daniil.project.controller;

import com.daniil.project.service.quiz.QuizService;
import com.daniil.project.service.score.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class QuizRESTControllerTest {

    @Mock
    private ScoreService scoreService;

    @InjectMocks
    private QuizRESTController quizRESTController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizRESTController).build();
    }

    @Test
    void globalScore() throws Exception {
        when(scoreService.getTotalScore()).thenReturn(10);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/global-score"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
        verify(scoreService, times(1)).getTotalScore();
    }

}
