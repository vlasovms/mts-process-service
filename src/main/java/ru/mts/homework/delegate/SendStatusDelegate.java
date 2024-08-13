package ru.mts.homework.delegate;

import lombok.Data;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.ApplicationStatus;

@Component
@Data
public class SendStatusDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, ApplicationStatus> kafkaTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        ApplicationStatus applicationStatus = new ApplicationStatus();
        applicationStatus.setId((Long) execution.getVariableLocal("id"));
        applicationStatus.setStatus((String) execution.getVariableLocal("status"));
        applicationStatus.setComment((String) execution.getVariableLocal("comment"));
        logger.info("Status message sended to kafka");
        kafkaTemplate.send("application.status", applicationStatus);
    }
}
