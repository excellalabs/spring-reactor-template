package com.excella.reactor.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excella.reactor.domain.Employee;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Test
public class ControllerSmokeTest extends AbstractTestNGSpringContextTests {

  private EmployeeController controller;

  @Autowired
  public ControllerSmokeTest(EmployeeController employeeController) {
    this.controller = employeeController;
  }

  @Test
  public void contextLoads() throws Exception {
    assert false;
    assertThat(controller).isNotNull();
  }


}
