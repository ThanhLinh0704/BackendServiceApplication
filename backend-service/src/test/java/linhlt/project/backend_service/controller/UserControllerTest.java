package linhlt.project.backend_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import linhlt.project.backend_service.config.MockConfig;
import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.response.UserResponse;
import linhlt.project.backend_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MockConfig.class)
@Slf4j
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private UserResponse userResponse;
    private UserRequest userRequest;

    @BeforeEach
    void initData() {
        userRequest = UserRequest.builder()
                .username("admin")
                .password("admin")
                .email("admin@admin.com")
                .firstName("linhlt")
                .lastName("admin")
                .build();

        userResponse = UserResponse.builder()
                .username("admin")
                .email("admin@admin.com")
                .firstName("linhlt")
                .lastName("admin")
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        //GIVEN

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(userRequest);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result.username")
                        .value("admin"));
        ;
        //THEN
    }
}
