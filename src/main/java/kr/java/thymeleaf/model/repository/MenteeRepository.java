package kr.java.thymeleaf.model.repository;

import kr.java.thymeleaf.model.entity.Mentee;
import kr.java.thymeleaf.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {

    // 특정 멘토의 멘티 목록 조회 (Mentor의 Id로 불러오겠다)
    List<Mentee> findByMentorId(Long mentorId);

    // 멘티와 담당 멘토 정보를 함께 조회
    @Query("SELECT m FROM Mentee m JOIN FETCH m.mentor WHERE m.id = :id")
    Optional<Mentee> findByIdWithMentor(@Param("id") Long id);
}
