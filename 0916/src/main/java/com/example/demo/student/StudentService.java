package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional=studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("이메일이 존재합니다.(email taken)");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		/*studentRepository.findById(studentId);
		studentRepository.existsById(studentId);*/
		Boolean exists=studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("student with id "+studentId+"does not exists");
		}
		studentRepository.deleteById(studentId);
	}
	
	//쿼리를 사용하지 않는 경우 
	//이 주석이 있으면 엔터티가 관리 상태가 되고 이 모든 것을 다룬다.
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student=studentRepository.findById(studentId)
				.orElseThrow(()-> new IllegalStateException("student with id "+studentId+"does not exist"));
		
		if(name !=null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("이메일이 존재합니다.(email taken)");
			}
			student.setEmail(email);
		}
	}
}
