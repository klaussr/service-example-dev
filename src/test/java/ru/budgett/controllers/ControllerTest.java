package ru.budgett.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.budgett.AbstractTest;
import ru.budgett.data.User;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static ru.budgett.helpers.UserHelper.createUser;

public class ControllerTest extends AbstractTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @Before
    public final void before() {
        if (this.mockMvc == null) {
            mockMvc = webAppContextSetup(webApplicationContext)
                    .alwaysDo(print())
                    .build();
        }
    }

    @Test
    public void testSaveUser() throws Exception {

        mockMvc.perform(post("/user/save")
                .contentType(APPLICATION_JSON)
                .content(toJson(createUser())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")))
        ;
    }

    @Test
    public void testDeleteUser() throws Exception {

        mockMvc.perform(delete("/user/delete/1")
                .contentType(APPLICATION_JSON)
                .content(toJson(createUser())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")))
        ;
    }


    @Test
    public void testGetUser() throws Exception {

        mockMvc.perform(get("/user/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("user1")))
                .andExpect(jsonPath("$.birthDate", is("2020-01-01")))
                .andExpect(jsonPath("$.isAdmin", is(false)))
        ;
    }

    @Test
    public void testGetUserPayments() throws Exception {

        mockMvc.perform(get("/user/payments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.user_id", is(1)))
                .andExpect(jsonPath("$.payment_number", is(1)))
                .andExpect(jsonPath("$.payment_date", is("2022-02-01")))
                .andExpect(jsonPath("$.payment_quantity", is(5273.23)))
                .andExpect(jsonPath("$.debt", is(4810.73)))
                .andExpect(jsonPath("$.procents", is(462.5)))
                .andExpect(jsonPath("$.commission", is(0.0)))
                .andExpect(jsonPath("$.rest_of_debt", is(25189.27)))
        ;
    }

    @Test
    public void testGetAllUser() throws Exception {

        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("user1")))
                .andExpect(jsonPath("$[0].birthDate", is("2020-01-01")))
                .andExpect(jsonPath("$[0].isAdmin", is(false)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("user2")))
                .andExpect(jsonPath("$[1].birthDate", is("2020-01-01")))
                .andExpect(jsonPath("$[1].isAdmin", is(false)))
        ;
    }

    @Test
    public void testSaveBadUser() throws Exception {
        mockMvc.perform(post("/user/save")
                .contentType(APPLICATION_JSON)
                .content(toJson(new User())))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.isAdmin").exists())
                .andExpect(jsonPath("$.birthDate").exists())
        ;
    }

    protected String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
