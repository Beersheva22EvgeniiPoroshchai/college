package telran.spring.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.spring.college.entity.Lecturer;

public interface LecturerRepositary extends JpaRepository<Lecturer, Long> {

	
}
