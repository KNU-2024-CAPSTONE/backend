package knu.project.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OutfluxAlgorithm {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // Member 객체와의 연관 관계 설정

    private LocalDateTime lastAccessTime;

    private boolean isOutflux;  // 이탈 여부 (true: 이탈, false: 유효)

    public void setIsOutflux(boolean isOutflux) {
    }

    // setIsOutflux 메서드가 Lombok @Setter 어노테이션 덕분에 자동으로 생성됩니다.
    // 만약 Lombok을 사용하지 않으면 직접 setIsOutflux(boolean isOutflux) 메서드를 추가해야 합니다.
}
