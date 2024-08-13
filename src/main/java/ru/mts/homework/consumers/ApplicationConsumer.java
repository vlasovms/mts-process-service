package ru.mts.homework.consumers;

import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.mts.homework.Application;

import java.util.Map;

@Configuration
public class ApplicationConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RuntimeService runtimeService;

    @KafkaListener(topics = "application", groupId = "consumer-1")
    public void consumeApplication(Application app) {
        logger.info("Process service consume application message:  {}", app);
        String extId = runtimeService.startProcessInstanceByKey("vacation-process", Map.of("id", app.getId(), "startDate", app.getStartDate().toString(), "endDate", app.getEndDate().toString(), "firstName", app.getEmpFirstName(), "secondName", app.getEmpSecondName())).getProcessInstanceId();
        logger.info("Camunda process started with id:  {}", extId);
    }
}
