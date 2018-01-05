package org.springframework.test.commands.remote;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.test.feign.RemoteServicesClient;

@Service
public class RemoteMessageAnnotationClient {

  private final RemoteServicesClient remoteServicesClient;

  @Autowired
  public RemoteMessageAnnotationClient(RemoteServicesClient remoteServicesClient) {
    this.remoteServicesClient = remoteServicesClient;
  }

  @HystrixCommand(fallbackMethod = "defaultMessage", commandKey = "RemoteMessageAnnotationClient")
  public MessageAcknowledgement sendMessage(Message message) {
    return this.remoteServicesClient.sendMessage(message);
  }

  public MessageAcknowledgement defaultMessage(Message message) {
    return new MessageAcknowledgement("-1", message.getPayload(), "Fallback Payload");
  }
}