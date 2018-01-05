package org.springframework.test.commands.collapsed;

import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

  public Person findPerson(int id) {
    return new Person(id, "Name: " + id);
  }

  public List<Person> findPersons(List<Integer> ids) {
    return ids.stream()
            .map(id -> new Person(id, "Name: " + id))
            .collect(Collectors.toList());
  }
}