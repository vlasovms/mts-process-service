package ru.mts.homework.consumer;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.mts.homework.Application;

import java.util.Map;

@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RuntimeService runtimeService;

    //   private final RuntimeService runtimeService;
    @KafkaListener(topics = "test", groupId = "consumer-1")
    public void consumeTest(Application app) {
        logger.info("Process service consume application:  {}", app);
        String extId = runtimeService.startProcessInstanceByKey("vacation-process", Map.of("id", app.getId(), "startDate", app.getStartDate().toString())).getProcessInstanceId();
        logger.info("Camunda process started with id:  {}", extId);
    }
}
