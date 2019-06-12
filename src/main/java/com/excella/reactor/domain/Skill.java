package com.excella.reactor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Value;

/**
 * This class represents an immutable skill, as defined in the domain, as opposed to a skill
 * possessed by an employee.
 */
@Value
@Entity
public class Skill implements Serializable {
  @Id private Long id;
  private String name;
  @Embedded private SkillCategory category;

  @JsonIgnore
  @OneToMany(mappedBy = "employee")
  Set<EmployeeSkill> employees;
}
