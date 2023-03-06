package com.lancaster.symptomsubchapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancaster.symptomsubchapter.controller.SubChapterController;
import com.lancaster.symptomsubchapter.model.SubChapter;
import com.lancaster.symptomsubchapter.service.SubChapterService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = SubChapterController.class)
public class SymptomSubchapterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SubChapterController subChapterController;

    @Mock
    private SubChapterService subChapterService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(subChapterController).build();
    }

    @Test
    public void testFindSubChapterWithName() throws Exception {
        SubChapter subChapter = new SubChapter(1, "test", 1);
        given(subChapterService.fetchSubChapterWithName("test")).willReturn(subChapter);
        mockMvc.perform(get("/subchapters/subchapter/{name}", "test"))
                .andExpect(status().isOk());
    }


    @Test
    public void testCreateSubChapter() throws Exception {
        // Create a new SubChapter object to use as the request body
        SubChapter subChapter = SubChapter.builder()
                .name("Test SubChapter")
                .chapterId(1)
                .build();
        // Mock the service method to return the ID of the created subchapter
        when(subChapterService.saveSubChapter(any(SubChapter.class))).thenReturn(1);

        // Send a POST request to the /create endpoint with the SubChapter object as the request body
        mockMvc.perform(post("/subchapters/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(subChapter)))
                .andExpect(status().isCreated()) // Verify that the response status is 201 CREATED
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify that the response content type is JSON
                .andExpect(jsonPath("$").value(1)); // Verify that the response body contains the ID of the created subchapter
    }


    @Test
    public void testUpdateSubChapter() throws Exception {
        // Create a new SubChapter object to use as the request body
        SubChapter subChapter = SubChapter.builder()
                .id(1)
                .name("Test SubChapter")
                .chapterId(1)
                .build();
        // Mock the service method to return the updated SubChapter object
        when(subChapterService.updateSubChapter(any(SubChapter.class), eq(1))).thenReturn(subChapter);

        // Send a PUT request to the /update/1 endpoint with the SubChapter object as the request body
        mockMvc.perform(put("/subchapters/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(subChapter)))
                .andExpect(status().isOk()) // Verify that the response status is 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify that the response content type is JSON
                .andExpect(jsonPath("$.id").value(1)) // Verify that the response body contains the updated SubChapter object
                .andExpect(jsonPath("$.name").value("Test SubChapter"))
                .andExpect(jsonPath("$.chapterId").value(1));
    }

}
