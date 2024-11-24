package knu.project.crm.influx.dto;

import java.time.LocalDate;

public record InfluxResponse(String gender, int age, LocalDate registerDate) {
}