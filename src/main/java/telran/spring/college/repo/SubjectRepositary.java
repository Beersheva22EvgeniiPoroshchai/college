package telran.spring.college.repo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.college.dto.IdName;
import telran.spring.college.entity.Subject;

public interface SubjectRepositary extends JpaRepository<Subject, String> {
	
	
}
