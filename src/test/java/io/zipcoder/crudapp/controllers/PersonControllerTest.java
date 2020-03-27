package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.Repository.PersonRepository;
import io.zipcoder.crudapp.models.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRepository repository;

    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person("Moe", "Aydin");

        BDDMockito
                .given(repository.save(person))
                .willReturn(person);

        String expectedContent = "{\"firstName\":\"Moe\",\"lastName\":\"Aydin\",\"id\":1}";
        this.mvc.perform(MockMvcRequestBuilders
                .post("/people/")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


}
