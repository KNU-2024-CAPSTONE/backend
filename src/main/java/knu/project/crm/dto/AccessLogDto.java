package knu.project.crm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AccessLogDto {
    private String email;
    private String gender;
    private int age;
    private LocalDateTime accessTime;

    // 기본 생성자
    public AccessLogDto() {}

    // 생성자
    public AccessLogDto(String email, String gender, int age, LocalDateTime accessTime) {
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.accessTime = accessTime;
    }

    // Getter, Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
