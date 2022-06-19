package com.sages.accountsuser.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sages.accountsuser.user.domain.User;
import com.sages.accountsuser.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("GET /users -> HTTP 200")
    void whenGetUser_thenReturn200() throws Exception {
        var endpointUrl = "/users/all";
        mockMvc.perform(
                MockMvcRequestBuilders.get(endpointUrl))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("GET /users -> HTTP 404")
    void whenGetUser_thenReturn404() throws Exception {
        var endpointUrl = "/user";
        mockMvc.perform(
                MockMvcRequestBuilders.get(endpointUrl)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("POST /users -> HTTP 201")
    void whenPostUser_thenReturn201() throws Exception {
        var endpointUrl = "/users/add";
        var initialListSize = repository.findAll().size();
        var firstName = "Marian";
        var lastName = "Kowal";
        var email = "kowal@gmail.com";
        var password = "hasło";
        var newUser = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password).build();
        var newUserAsJSON = objectMapper.writeValueAsString(newUser);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post(endpointUrl)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newUserAsJSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        assertAll("should add one user",
                () -> assertEquals(initialListSize + 1, repository.findAll().size()),
                () -> assertEquals(firstName, repository.findAll().get(initialListSize).getFirstName()),
                () -> assertEquals(lastName, repository.findAll().get(initialListSize).getLastName()),
                () -> assertEquals(email, repository.findAll().get(initialListSize).getEmail())
        );
    }
}
