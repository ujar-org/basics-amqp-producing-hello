package org.ujar.basics.amqp.producing.hello.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ujar.basics.amqp.producing.hello.consumer.GreetingMessageConsumer;

@Configuration
@RequiredArgsConstructor
public class AmqpConsumingTestConfig {
  private final AmqpQueuesProperties queues;

  @Bean
  public SimpleMessageListenerContainer greeterMessageListenerContainer(
      final ConnectionFactory connectionFactory, final MessageListenerAdapter greeterMessageListenerAdapter) {
    final var container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queues.getGreeterQueue());
    container.setMessageListener(greeterMessageListenerAdapter);
    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
    container.setDefaultRequeueRejected(false);
    return container;
  }

  @Bean
  public MessageListenerAdapter greeterMessageListenerAdapter(final GreetingMessageConsumer consumer) {
    return new MessageListenerAdapter(consumer, "consume");
  }
}
