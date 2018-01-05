package org.springframework.test.commands.obs;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

public class HelloWorldObservableCommand extends HystrixObservableCommand<String> {

  private final String name;

  public HelloWorldObservableCommand(String name) {
    super(HystrixCommandGroupKey.Factory.asKey("HelloWorldObservableCommand"));
    this.name = name;
  }

  @Override
  protected Observable<String> resumeWithFallback() {
    return Observable.just("Returning a fallback");
  }

  @Override
  protected Observable<String> construct() {
    return Observable.just("Hello " + name);
  }
}