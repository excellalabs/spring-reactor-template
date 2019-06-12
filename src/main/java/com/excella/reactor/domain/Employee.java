package com.excella.reactor.domain;

import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends DomainModel {
  @Embedded private Bio bio;

  @Embedded private Contact contact;

  @OneToMany(mappedBy = "skill")
  Set<EmployeeSkill> skills;
}
