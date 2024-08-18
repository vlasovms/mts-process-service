package ru.mts.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Application {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate endDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String empFirstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String empSecondName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
}
