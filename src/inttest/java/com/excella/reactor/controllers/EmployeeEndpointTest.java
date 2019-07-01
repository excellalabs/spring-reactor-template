package com.excella.reactor.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excella.reactor.domain.Address;
import com.excella.reactor.domain.Bio;
import com.excella.reactor.domain.Contact;
import com.excella.reactor.domain.Employee;
import com.excella.reactor.domain.EmployeeSkill;
import com.excella.reactor.domain.Ethnicity;
import com.excella.reactor.domain.Gender;
import com.excella.reactor.domain.Skill;
import com.excella.reactor.domain.SkillCategory;
import com.excella.reactor.domain.SkillProficiency;
import com.excella.reactor.util.TestSecUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import java.time.LocalDate;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class EmployeeEndpointTest extends AbstractTestNGSpringContextTests {
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper mapper;
  @Autowired private TestSecUtils testSecUtils;
  @Autowired private EmployeeController employeeController;

  private Employee employee;
  private String authToken;



  @BeforeClass
  public void beforeClass() {
    this.authToken = testSecUtils.getAuth(mockMvc);
  }

  @BeforeMethod
  public void beforeTest() {
    employee = new Employee();
    var bio = new Bio();
    var contact = new Contact();
    var employeeSkill = new EmployeeSkill();
    var skill = new Skill();
    var address = new Address();
    var skillCategory = new SkillCategory();

    skillCategory.setId(1L);
    skillCategory.setName("Agile");
    skill.setId(1L);
    skill.setName("Scrum Master");
    skill.setCategory(skillCategory);
    employee.setBio(bio);
    employee.setContact(contact);
    employee.setSkills(Collections.singletonList(employeeSkill));

    bio.setEthnicity(Ethnicity.CAUCASIAN);
    bio.setFirstName("John");
    bio.setLastName("Doe");
    bio.setGender(Gender.MALE);
    bio.setUsCitizen(Boolean.TRUE);
    bio.setBirthDate(LocalDate.now().minusYears(18));

    contact.setAddress(address);
    contact.setEmail("john.doe@test.com");
    contact.setPhoneNumber("5715555555");

    address.setLine1("1 Fake St");
    address.setCity("Portsmouth");
    address.setStateCode("VA");
    address.setZipCode("23523");

    employeeSkill.setSkill(skill);
    employeeSkill.setProficiency(SkillProficiency.HIGH);
    employeeSkill.setPrimary(Boolean.TRUE);

    skill.setId(1L);
  }

  @Test
  public void contextLoads() {
    assert mockMvc != null;
    assert mapper != null;
    assert employeeController != null;
  }

  @Test(description = "Should reject unauthorized user.")
  public void authError() throws Exception {
    mockMvc
        .perform(
            post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  public void success() throws Exception {
    mockMvc
        .perform(
            post("/employee")
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authToken))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(employee)))
        .andDo(print())
        .andExpect(status().is2xxSuccessful());
  }
}
