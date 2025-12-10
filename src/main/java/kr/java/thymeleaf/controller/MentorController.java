package kr.java.thymeleaf.controller;

import kr.java.thymeleaf.model.entity.Mentor;
import kr.java.thymeleaf.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    // 기본 문법 학습용 페이지
    @GetMapping("/syntax/basic")
    public String basicSyntax(Model model) {
        Mentor mentor = mentorService.findByIdWithMentees(1L);

        // Model에 데이터 추가 (View에서 접근 가능)
        model.addAttribute("mentor", mentor);
        model.addAttribute("greeting", "안녕하세요!");
        model.addAttribute("currentTime", LocalDateTime.now());

        return "syntax/basic";  // templates/syntax/basic.html
    }

    @GetMapping("/mentor/list")
    public String mentorList(Model model) {
        List<Mentor> mentors = mentorService.findAllWithMentees();
        model.addAttribute("mentors", mentors);
        model.addAttribute("pageTitle", "멘토 관리 시스템"); // SEO 개념
        return "mentor/list";
    }

    @GetMapping("/mentor/{id}")
//    @ResponseBody // JSON Return
    public String mentorDetail(@PathVariable Long id, Model model) {
//    public Mentor mentorDetail(@PathVariable Long id, Model model) {
        Mentor mentor = mentorService.findByIdWithMentees(id);
        model.addAttribute("mentor", mentor);

         return "mentor/detail";
//        return mentorService.findByIdWithMentees(id); // 접속했을때, view 없이도 접근 가능함 (JSON형태로 반환)
    }

}
