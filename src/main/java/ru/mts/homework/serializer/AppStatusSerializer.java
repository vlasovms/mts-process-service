package ru.mts.homework.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import ru.mts.homework.ApplicationStatus;

public class AppStatusSerializer implements Serializer<ApplicationStatus> {
    @Override
    public byte[] serialize(String arg0, ApplicationStatus applicationStatus) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            retVal = objectMapper.writeValueAsString(applicationStatus).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

}
