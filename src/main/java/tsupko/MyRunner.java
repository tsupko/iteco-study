package tsupko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    private final Kafka kafka;

    @Autowired
    public MyRunner(Kafka kafka) {
        this.kafka = kafka;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        kafka.sendMessage();
        kafka.receiveMessage("Alex");
    }
}
