package com.excella.reactor.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excella.reactor.domain.Employee;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
@AutoConfigureMockMvc
public class EmployeeEndpointTest extends AbstractTestNGSpringContextTests {
  private MockMvc mockMvc;
  private ObjectMapper mapper;

  private Employee employee;

  @Autowired
  public EmployeeEndpointTest(MockMvc mockMvc, ObjectMapper mapper) {
    this.mockMvc = mockMvc;
    this.mapper = mapper;
  }

  @BeforeClass
  public void beforeClass() {
    this.employee = new Employee();
  }

  @BeforeTest
  public void beforeTest() {}

  @Test
  public void contextLoads() {
    assert false;
    assert mockMvc != null;
  }

  @Test(description = "Post an employee successfully.")
  public void postSuccessfully() throws Exception {
    assert false;
    mockMvc
        .perform(
            post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(employee)))
        .andExpect(status().is4xxClientError());
  }
}
