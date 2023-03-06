package com.lancaster.symptomchapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancaster.keywordsymptoms.controller.KeywordClassificationController;
import com.lancaster.keywordsymptoms.model.KeywordClassifiedSymptom;
import com.lancaster.keywordsymptoms.service.KeywordClassificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = KeywordClassificationController.class)
public class KeywordClassificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private KeywordClassificationController controller;

    @Mock
    private KeywordClassificationService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindClassifiedSymptomWithId() throws Exception {
        KeywordClassifiedSymptom symptom = new KeywordClassifiedSymptom(4, 1, 2, 2, 2);
        given(service.fetchClassifiedSymptomWitSymptomId(1)).willReturn(symptom);
        mockMvc.perform(get("/classifiedSymptoms/classifiedSymptom/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClassifiedSymptom() throws Exception {
        KeywordClassifiedSymptom symptom = new KeywordClassifiedSymptom(1, 1, 1, 2, 3);

        Optional<KeywordClassifiedSymptom> mockOptionalSymptom = Optional.of(new KeywordClassifiedSymptom(1, 1, 1, 2, 3));
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(put("/classifiedSymptoms/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(symptom)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.symptomId", is(1)))
//                .andExpect(jsonPath("$.chapterId", is(1)))
//                .andExpect(jsonPath("$.subchapterId", is(2)))
//                .andExpect(jsonPath("$.secondsubId", is(3)));

//        verify(service, times(1)).updateClassifiedSymptom(symptom, "Fever");
//        verifyNoMoreInteractions(service);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
