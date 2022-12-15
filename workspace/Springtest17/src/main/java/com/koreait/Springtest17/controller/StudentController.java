package com.koreait.Springtest17.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.Springtest17.StudentDTO;
import com.koreait.Springtest17.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {

	private final StudentRepository studentRepository;
	
	@PostConstruct
	public void student() {
		studentRepository.save(new StudentDTO("학생1", 15, 6, "01012345670", "서울"));
	}
	
	@GetMapping("students")
	public String home(Model model) {
		model.addAttribute("studentList", studentRepository.findAll());
		return "students";
	}
	
	@GetMapping("/studentAddForm")
	public String addForm(Model model) {
		StudentDTO student = new StudentDTO();
		model.addAttribute("student", student);
		return "/studentAddForm";
	}
	
	@PostMapping("/studentAddForm")
	public String save(StudentDTO student,BindingResult bindingResult,  RedirectAttributes redirectAttributes) {
		
		if(!StringUtils.hasText(student.getStudentName())) {
			bindingResult.addError(new FieldError("student", "name", "학생이름을 입력하세요"));
		}
		if(student.getAge() == null) {
			bindingResult.addError(new FieldError("student", "age", "나이를 입력하세요"));
		}
		if(student.getSubject() == null) {
			bindingResult.addError(new FieldError("student", "subject", "과목수를 입력하세요"));
		}
		if(!StringUtils.hasText(student.getPhone())) {
			bindingResult.addError(new FieldError("student", "phone", "전화번호를 입력하세요"));
		}
		if(!StringUtils.hasText(student.getAddr())) {
			bindingResult.addError(new FieldError("student", "address", "주소를 입력하세요"));
		}
		
		 if(bindingResult.hasErrors()) {
	        	return "studentAddForm";
	     }
		
		StudentDTO saveStudent =  studentRepository.save(student);
		redirectAttributes.addAttribute("studentId", saveStudent.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/{studentId}";
		
	}
	
		@GetMapping("/{studentId}")
		public String findOneById(@PathVariable Long studentId, Model model) {
			model.addAttribute("findOne",studentRepository.findById(studentId) );
			return "student";
		}
		
		@GetMapping("/{studentId}/edit")
		public String edit(@PathVariable Long studentId, Model model) {
			model.addAttribute("student", studentRepository.findById(studentId));
			return "studentEditForm";
		}
		
		@PostMapping("/{studentId}/edit")
		public String editSave(@ModelAttribute StudentDTO student, @PathVariable Long studentId) {
			studentRepository.update(student, studentId);
			return "redirect:/{studentId}";
		}
	}

