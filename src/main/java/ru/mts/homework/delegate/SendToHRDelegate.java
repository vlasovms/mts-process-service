package ru.mts.homework.delegate;

import lombok.Data;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.dto.Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Data
public class SendToHRDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, Application> kafkaTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        Application application = new Application();
        application.setId((Long) execution.getVariableLocal("id"));
        application.setStartDate(LocalDate.parse((String) execution.getVariableLocal("startDate"), formatter));
        application.setEndDate(LocalDate.parse((String) execution.getVariableLocal("endDate"), formatter));
        kafkaTemplate.send("application.hr.request", application);
        logger.info("Application sended to application.hr.request");
    }
}
