package org.springframework.test.service;

import org.springframework.test.models.Message;
import org.springframework.test.models.MessageAcknowledgement;
import rx.Observable;

public interface MessageHandlerService {
  Observable<MessageAcknowledgement> handleMessage(Message message);
}