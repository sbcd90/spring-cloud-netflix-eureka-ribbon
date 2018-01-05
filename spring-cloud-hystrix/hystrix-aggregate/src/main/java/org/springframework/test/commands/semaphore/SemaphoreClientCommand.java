package org.springframework.test.commands.semaphore;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.test.feign.RemoteServicesClient;

public class SemaphoreClientCommand extends HystrixCommand<MessageAcknowledgement> {

  private final RemoteServicesClient remoteServicesClient;
  private final Message message;

  public SemaphoreClientCommand(RemoteServicesClient remoteServicesClient, Message message) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreClientCommand"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                          .withExecutionTimeoutInMilliseconds(5000)
                                          .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
    this.remoteServicesClient = remoteServicesClient;
    this.message = message;
  }

  @Override
  protected MessageAcknowledgement run() {
    return this.remoteServicesClient.sendMessage(message);
  }

  @Override
  protected MessageAcknowledgement getFallback() {
    return new MessageAcknowledgement(message.getId(), message.getPayload(), "Fallback message");
  }
}