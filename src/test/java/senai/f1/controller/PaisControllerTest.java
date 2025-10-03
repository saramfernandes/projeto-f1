package senai.f1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PaisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create() throws Exception {

        String json = """
            {
                "nome": "China"
            }
        """;

        mockMvc.perform(post("/paises")
                        .contentType("application/json")
                        .content(json))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.nome").value("China"));
    }
}