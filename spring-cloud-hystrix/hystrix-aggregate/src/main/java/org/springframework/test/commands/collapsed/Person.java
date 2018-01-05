package org.springframework.test.commands.collapsed;

import java.io.Serializable;

public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;
  private String name;

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer("Person{");
    sb.append("id=").append(id);
    sb.append(", name=").append(name);
    sb.append("}");
    return sb.toString();
  }
}