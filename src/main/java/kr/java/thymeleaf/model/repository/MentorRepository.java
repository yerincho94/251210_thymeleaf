package kr.java.thymeleaf.model.repository;

import kr.java.thymeleaf.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// JpaRepository : save(), findAll() 등 -> 기본 메서드가 내장되어 있음
public interface MentorRepository extends JpaRepository<Mentor, Long> {

    // Fetch Join을 사용하여 멘토와 멘티를 한 번에 조회
    // DISTINCT: 중복 결과 제거 (1:N 조인 시 발생할 수 있는 중복)
    @Query("SELECT DISTINCT m FROM Mentor m LEFT JOIN FETCH m.mentees")
    List<Mentor> findAllWithMentees();

    // 특정 멘토와 소속 멘티들을 함께 조회
    @Query("SELECT m FROM Mentor m LEFT JOIN FETCH m.mentees WHERE m.id = :id")
    Optional<Mentor> findByIdWithMentees(@Param("id") Long id);

}
