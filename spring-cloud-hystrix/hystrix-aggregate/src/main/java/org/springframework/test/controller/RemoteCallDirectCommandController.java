package org.springframework.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.commands.remote.RemoteMessageClientCommand;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.test.feign.RemoteServicesClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteCallDirectCommandController {

  @Autowired
  private RemoteServicesClient remoteServicesClient;

  public MessageAcknowledgement sendMessage(Message message) {
    RemoteMessageClientCommand remoteCallCommand = new RemoteMessageClientCommand(remoteServicesClient, message);
    return remoteCallCommand.execute();
  }
}