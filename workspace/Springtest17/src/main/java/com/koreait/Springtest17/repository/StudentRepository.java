package com.koreait.Springtest17.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.koreait.Springtest17.StudentDTO;

@Repository
public class StudentRepository {

	private static final Map<Long, StudentDTO> store = new HashMap<Long, StudentDTO>();
	private static long sequence = 0L;
	
	
	public StudentDTO save(StudentDTO student) {
		student.setId(++sequence);
		store.put(student.getId(), student);
		return student;
	}
	
	public List<StudentDTO> findAll() {
		return new ArrayList<StudentDTO>(store.values());
	}

	public StudentDTO findById(Long studentId) {
		return store.get(studentId);
		
	}

	public void update(StudentDTO student, Long studentId) {
		StudentDTO findStudent = findById(studentId);
		findStudent.setStudentName(student.getStudentName());
		findStudent.setAge(student.getAge());
		findStudent.setSubject(student.getSubject());
		findStudent.setPhone(student.getPhone());
		findStudent.setAddr(student.getAddr());
		
	}

}
