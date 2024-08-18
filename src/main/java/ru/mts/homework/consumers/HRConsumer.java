package ru.mts.homework.consumers;

import lombok.Data;
import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.mts.homework.dto.Application;

@Data
@Component
public class HRConsumer {
    @Autowired
    private RuntimeService runtimeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "application.hr.result", groupId = "consumer-1")
    public void consumeHRDecision(Application app) {
        logger.info("Process service consume HR message:  {}", app);
        try {
            runtimeService.createMessageCorrelation("HRComplete")
                    .processInstanceBusinessKey(app.getId().toString())
                    .setVariable("status", app.getStatus())
                    .setVariable("comment", app.getComment())
                    .correlate();
        } catch (MismatchingMessageCorrelationException e) {
            logger.error("Error when correlating the message: {}", e.getMessage());
        }
    }
}
