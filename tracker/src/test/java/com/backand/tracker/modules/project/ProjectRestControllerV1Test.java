package com.backand.tracker.modules.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-project-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class ProjectRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        System.out.println("---SetUp---");
    }

    @AfterEach
    void tearDown() {
        System.out.println("---TearDown---");
    }

    @Test
    @WithMockUser(username = "timon", password = "timon")
    void getById() throws Exception {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("name", "test");
        responseBody.put("descriptions", "test");
        responseBody.put("creatorId", 1);
        responseBody.put("tasks", new Object[0]);
        responseBody.put("userProjects", new Object[0]);
        responseBody.put("projectRoles", new Object[0]);
        String responseBodyJson = new ObjectMapper().writeValueAsString(responseBody);

        this.mockMvc
                .perform(get("/api/v1/projects/1"))
                .andDo(print())
                .andDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(responseBodyJson));
    }

    @Test
    @WithMockUser(username = "timon", password = "timon")
    void createNewProject() throws Exception {
        MockMultipartFile avatarImage = new MockMultipartFile(
                "avatarImage",
                "test.jpg",
                "image/jpeg",
                "test image".getBytes());


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "test2");
        requestBody.put("descriptions", "test");
        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        MockMultipartFile requestBodyFile = new MockMultipartFile(
                "reqDto",
                "null",
                "application/json",
                requestBodyJson.getBytes());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("name", "test2");
        responseBody.put("descriptions", "test");
        responseBody.put("creatorId", 1);
        responseBody.put("tasks", new Object[0]);
        responseBody.put("userProjects", new Object[0]);
        responseBody.put("projectRoles", new Object[0]);
        String responseBodyJson = new ObjectMapper().writeValueAsString(responseBody);

        this.mockMvc
                .perform(multipart("/api/v1/projects")
                        .file(avatarImage)
                        .file(requestBodyFile))
                .andDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(responseBodyJson));
    }
}