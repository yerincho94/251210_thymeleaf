package kr.java.thymeleaf.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MENTEE")
@Getter @Setter @NoArgsConstructor
public class Mentee extends BaseEntity{
    private String name;    // 멘티 이름
    private String goal;    // 학습 목표
    private Integer age;    // 나이

    // form에서 setter로 하나씩 넣는게 귀찮아서 넣는 생성자
    public Mentee(String name, String goal, Integer age) {
        this.name = name;
        this.goal = goal;
        this.age = age;
    }

    // N:1 관계 - 여러 멘티가 한 멘토에게 속함
    // fetch = LAZY: 멘토 정보는 실제로 접근할 때만 조회
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")  // 외래키 컬럼명 지정
    private Mentor mentor;
}
