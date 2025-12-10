package kr.java.thymeleaf.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENTOR")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Mentor extends BaseEntity {
    private String name;        // 멘토 이름
    private String specialty;   // 전문 분야 (예: "백엔드", "프론트엔드")
//    @Column(unique = true)
    private String email;       // 이메일

    // form에서 setter로 하나씩 넣는게 귀찮아서 넣는 생성자
    public Mentor(String name, String specialty, String email) {
        this.name = name;
        this.specialty = specialty;
        this.email = email;
    }

    // 1:N 관계 - 한 멘토가 여러 멘티를 가짐
    // mappedBy: 관계의 주인이 아님을 표시 (Mentee의 mentor 필드가 주인)
    // fetch = LAZY: 멘티 목록은 실제로 접근할 때만 조회 (성능 최적화)
    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private List<Mentee> mentees = new ArrayList<>();
}
