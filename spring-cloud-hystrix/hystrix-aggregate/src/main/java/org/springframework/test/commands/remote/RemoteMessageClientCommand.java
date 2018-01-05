package org.springframework.test.commands.remote;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.test.feign.RemoteServicesClient;

public class RemoteMessageClientCommand extends HystrixCommand<MessageAcknowledgement> {

  private final RemoteServicesClient remoteServicesClient;
  private final Message message;

  public RemoteMessageClientCommand(RemoteServicesClient remoteServicesClient,
                                    Message message) {
    super(HystrixCommandGroupKey.Factory.asKey("RemoteMessageClientCommand"));
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