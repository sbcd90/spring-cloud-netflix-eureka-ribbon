package org.springframework.test.commands.collapsed;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonRequestCollapser extends HystrixCollapser<List<Person>, Person, Integer> {

  private final int id;

  public PersonRequestCollapser(int id) {
    super(Setter
        .withCollapserKey(HystrixCollapserKey.Factory.asKey("PersonRequestCollapser"))
        .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(2000)));
    this.id = id;
  }

  @Override
  public Integer getRequestArgument() {
    return id;
  }

  @Override
  protected HystrixCommand<List<Person>> createCommand(Collection<CollapsedRequest<Person, Integer>> collection) {
    List<Integer> ids = collection.stream()
                          .map(cr -> cr.getArgument())
                          .collect(Collectors.toList());
    return new PersonRequestCommand(ids);
  }

  @Override
  protected void mapResponseToRequests(List<Person> people, Collection<CollapsedRequest<Person, Integer>> collection) {
    Map<Integer, Person> personMap = people.stream().collect(Collectors.toMap(Person::getId, Function.identity()));

    for (CollapsedRequest<Person, Integer> request: collection) {
      request.setResponse(personMap.get(request.getArgument()));
    }
  }
}