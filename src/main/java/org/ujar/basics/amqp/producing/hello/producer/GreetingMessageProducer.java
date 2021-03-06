package org.ujar.basics.amqp.producing.hello.producer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.ujar.basics.amqp.producing.hello.config.AmqpQueuesProperties;
import org.ujar.basics.amqp.producing.hello.model.Greeting;

@Component
@Slf4j
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP2")
public class GreetingMessageProducer {

  private final RabbitTemplate template;
  private final AmqpQueuesProperties topicDefinitions;

  public void send(Greeting message) {
    log.info("( {} ) Send message, value: {}", Thread.currentThread().getName(), message);
    template.convertAndSend(topicDefinitions.getGreeterExchange(), "greeting", message);
  }
}
