package ru.mts.homework.delegate;

import lombok.Data;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.dto.Application;

@Component
@Data
public class SendStatusDelegate implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, Application> kafkaTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Application application = new Application();
        application.setId((Long) execution.getVariableLocal("id"));
        application.setStatus((String) execution.getVariableLocal("status"));
        application.setComment((String) execution.getVariableLocal("comment"));
        kafkaTemplate.send("application.status", application);
        logger.info("Application sended to application.status");
    }
}
