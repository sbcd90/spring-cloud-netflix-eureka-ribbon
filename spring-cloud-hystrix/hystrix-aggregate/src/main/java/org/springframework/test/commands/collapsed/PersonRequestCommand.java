package org.springframework.test.commands.collapsed;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

public class PersonRequestCommand extends HystrixCommand<List<Person>> {

  private final List<Integer> ids;
  private final PersonService personService = new PersonService();

  public PersonRequestCommand(List<Integer> ids) {
    super(HystrixCommandGroupKey.Factory.asKey("PersonRequestCommand"));
    this.ids = ids;
  }

  @Override
  protected List<Person> run() {
    return personService.findPersons(ids);
  }
}