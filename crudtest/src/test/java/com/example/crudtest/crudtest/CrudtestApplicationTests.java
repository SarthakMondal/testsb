package com.example.crudtest.crudtest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CrudtestApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    String mainUrl = "http://localhost:8080/backend";

    @Test
    @Order(1)
    void addStudentTest() throws Exception {

        String sModel = "{\"name\" : \"Gunjan Ganguly\", \"year\" : \"4th Year\",\"marks\": 82}";

        this.mockMvc
                .perform(
                        post(mainUrl + "/addstudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(sModel))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gunjan Ganguly"))
                .andExpect(jsonPath("$.year").value("4th Year"))
                .andExpect(jsonPath("$.marks").value(82))
                .andDo(print());
    }

    @Test
    @Order(2)
    void updateStudentTest() throws Exception {

        String sModel = "{\"name\" : \"Sourav Bhadra\", \"year\" : \"4th Year\",\"marks\": 80}";

        this.mockMvc
                .perform(
                        put(mainUrl + "/updatestudent/" + "2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(sModel))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sourav Bhadra"))
                .andExpect(jsonPath("$.year").value("4th Year"))
                .andExpect(jsonPath("$.marks").value(80))
                .andDo(print());
    }

    @Test
    @Order(3)
    void getAllStudentsTest() throws Exception {

        this.mockMvc
                .perform(
                        get(mainUrl + "/getstudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sourav Bhadra"))
                .andExpect(jsonPath("$[0].year").value("4th Year"))
                .andExpect(jsonPath("$[0].marks").value(80))
                .andDo(print());
    }

    @Test
    @Order(4)
    void getStudentDetailsTest() throws Exception {

        this.mockMvc
                .perform(
                        get(mainUrl + "/getstudentdetails/" + "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sourav Bhadra"))
                .andExpect(jsonPath("$.year").value("4th Year"))
                .andExpect(jsonPath("$.marks").value(80))
                .andDo(print());
    }

    @Test
    @Order(5)
    void deleteStudentDetailsTest() throws Exception {

        this.mockMvc
                .perform(
                        delete(mainUrl + "/deletestudent/" + "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sourav Bhadra"))
                .andExpect(jsonPath("$.year").value("4th Year"))
                .andExpect(jsonPath("$.marks").value(80))
                .andDo(print());
    }
}
