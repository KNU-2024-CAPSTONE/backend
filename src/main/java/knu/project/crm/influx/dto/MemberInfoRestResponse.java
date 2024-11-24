package knu.project.crm.influx.dto;

import java.time.LocalDate;

public class MemberInfoRestResponse {
    Long id;
    String email;
    String gender;
    int age;
    LocalDate registerDate;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public InfluxResponse mapToResponse(){
        return new InfluxResponse(this.gender, this.age, this.registerDate);
    }
}