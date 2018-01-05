package org.springframework.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.models.Message;
import org.springframework.test.models.MessageAcknowledgement;
import rx.Observable;

import java.util.concurrent.TimeUnit;

@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {

  @Value("${reply.message}")
  private String replyMessage;

  @Override
  public Observable<MessageAcknowledgement> handleMessage(Message message) {
    return Observable.timer(message.getDelayBy(), TimeUnit.MILLISECONDS)
            .map(l -> message.isThrowException())
            .map(throwException -> {
              if (throwException) {
                throw new RuntimeException("Throwing an exception");
              }
              return new MessageAcknowledgement(message.getId(), message.getPayload(), replyMessage);
            });
  }
}