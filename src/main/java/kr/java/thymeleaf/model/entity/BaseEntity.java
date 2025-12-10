package kr.java.thymeleaf.model.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;

// 엔티티는 아닌데, 엔티티처럼 작동하는 아이, 주로 자주 쓰이는 애들 여기에다가 선언함
@MappedSuperclass // 상속(extends) <- Table을 만들지 않고 나중에서 상속해서 사용
@Getter // Setter가 필요 없는 속성들
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Instant createdAt; // Instant -> UTC 기준
    private LocalDateTime createdAt; // 서버 시간 기준으로

    @PrePersist
    public void prePersist() { // 신규 save 되면 (INSERT로 실제 반영되기 직전)
        this.createdAt = LocalDateTime.now();
    }
}
