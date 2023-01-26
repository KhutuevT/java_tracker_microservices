package com.backand.tracker.modules.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-project-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-task-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class TaskRestControllerV1Test {

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
        responseBody.put("description", "test");
        responseBody.put("projectId", 1);
        responseBody.put("creatorId", 1);
        responseBody.put("timeSlices", new Object[0]);
        responseBody.put("userTasks", new Object[0]);
        responseBody.put("taskRoles", new Object[0]);
        String responseBodyJson = new ObjectMapper().writeValueAsString(responseBody);

        this.mockMvc
                .perform(get("/api/v1/projects/1/tasks/1"))
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
    void getAllByProjectId() throws Exception {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("name", "test");
        responseBody.put("description", "test");
        responseBody.put("projectId", 1);
        responseBody.put("creatorId", 1);
        responseBody.put("timeSlices", new Object[0]);
        responseBody.put("userTasks", new Object[0]);
        responseBody.put("taskRoles", new Object[0]);
        Object[] responseBodyArray = {responseBody};
        String responseBodyJson = new ObjectMapper().writeValueAsString(responseBodyArray);

        this.mockMvc
                .perform(get("/api/v1/projects/1/tasks"))
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
    void createNewTask() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "test2");
        requestBody.put("descriptions", "test2");
        String requestBodyJson = new ObjectMapper().writeValueAsString(requestBody);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("name", "test2");
        responseBody.put("description", "test2");
        responseBody.put("projectId", 1);
        responseBody.put("creatorId", 1);
        responseBody.put("timeSlices", new Object[0]);
        responseBody.put("userTasks", new Object[0]);
        responseBody.put("taskRoles", new Object[0]);
        String responseBodyJson = new ObjectMapper().writeValueAsString(responseBody);

        this.mockMvc
                .perform(post("/api/v1/projects/1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(responseBodyJson));
    }
}