package com.excella.reactor.controllers;

import com.excella.reactor.config.SecurityProperties;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthTest extends AbstractTestNGSpringContextTests {

  private MockMvc mockMvc;
  private SecurityProperties securityProperties;

  @Autowired
  public AuthTest(MockMvc mockMvc, SecurityProperties securityProperties) {
    this.mockMvc = mockMvc;
    this.securityProperties = securityProperties;
  }

  @Test
  public void shouldReturnDefaultMessage() {
    assert false;
    try {
      this.mockMvc.perform(post("/oauth/token")
          .with(SecurityMockMvcRequestPostProcessors.httpBasic(
              securityProperties.getOauth2().getClient().getClientId(),
              securityProperties.getOauth2().getClient().getClientSecret()))
          .contentType(MediaType.APPLICATION_FORM_URLENCODED)
          .content(buildUrlEncodedFormEntity(
              Map.of(
                  "grant_type", "password",
                  "username", "user",
                  "password", "pass"
              )))).andDo(print()).andExpect(status().isOk());
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  private String buildUrlEncodedFormEntity(Map<String, String> values) throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, String> value : values.entrySet()) {
      result.append(String.format("&%s=%s",
          URLEncoder.encode(value.getKey(), StandardCharsets.UTF_8.name()),
          URLEncoder.encode(value.getValue(), StandardCharsets.UTF_8.name())
      ));
    }
    return result.toString();
  }
}
