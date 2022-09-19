package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

	//select * from student where email=?
	//1의 의미는 1명을 말한다.(사실 잘 모름)
	@Query("SELECT s FROM Student s WHERE s.email=?1")
	Optional<Student> findStudentByEmail(String email);
}
