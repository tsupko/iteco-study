package tsupko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Kafka {
    private final ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;
    private final KafkaTemplate<String, String> kafkaMessageTemplate;

    @Autowired
    public Kafka(ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory, KafkaTemplate<String, String> kafkaMessageTemplate) {
        this.kafkaListenerContainerFactory = kafkaListenerContainerFactory;
        this.kafkaMessageTemplate = kafkaMessageTemplate;
    }

    public void receiveMessage(String username) {
        ConcurrentMessageListenerContainer<String, String> container = kafkaListenerContainerFactory.createContainer(username);
        container.getContainerProperties().setGroupId(username);
        container.setupMessageListener((MessageListener<String, String>) x -> System.out.printf("%s: %s", x.key(), x.value()));
        container.start();
    }

    public void sendMessage() {
        kafkaMessageTemplate.send("Alex", "Hello, World");
    }
}
