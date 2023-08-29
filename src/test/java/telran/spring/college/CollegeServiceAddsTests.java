package telran.spring.college;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.time.LocalDate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.spring.college.dto.MarkDto;
import telran.spring.college.dto.PersonDto;
import telran.spring.college.dto.SubjectDto;
import telran.spring.college.dto.SubjectType;
import telran.spring.college.repo.*;
import telran.spring.college.service.CollegeService;
import telran.spring.exceptions.NotFoundException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollegeServiceAddsTests {
private static final long ID_LECTURER = 123;
private static final long ID_STUDENT = 124;
@Autowired
CollegeService collegeService;
@Autowired
LecturerRepositary lecturerRepositary;
@Autowired
StudentRepositary studentRepositary;
PersonDto lecturerDto = new PersonDto(ID_LECTURER, "Vasya", LocalDate.now().toString(), null, null);
PersonDto lecturerDto1 = new PersonDto(null, "Sara", "2000-01-01", null, null);
//Lecturer lecturer = Lecturer.of(lecturerDto);
PersonDto studentDto = new PersonDto(ID_STUDENT, "Vasya", LocalDate.now().toString(), null, null);
PersonDto studentDto1 = new PersonDto(null, "Yosef", "2000-01-01", null, null);
//Student student = Student.of(studentDto);
SubjectDto subjectDto = new SubjectDto("S1", "Java", 100, null, SubjectType.BACK_END);
SubjectDto subjectDto1 = new SubjectDto("S2", "Java", 100, ID_LECTURER, SubjectType.BACK_END);
SubjectDto subjectDto2 = new SubjectDto("S3", "Java", 100, ID_LECTURER+10, SubjectType.BACK_END);
SubjectDto subjectDto3 = new SubjectDto("S1", "Java", 100, null, SubjectType.BACK_END);
	
	@Test
	@Order(1)
	void addLecturerTest() {
		PersonDto lectureActual = collegeService.addLecturer(lecturerDto);
		assertEquals(lectureActual.getId(), ID_LECTURER);
		PersonDto lectureActual1 = collegeService.addLecturer(lecturerDto1);
		assertEquals(lectureActual1.getName(), "Sara");
		assertThrows(Exception.class, () -> collegeService.addLecturer(lecturerDto));
		
	}
	
	@Test
	@Order(2)
	void addStudentTest() {
		PersonDto studentActual = collegeService.addStudent(studentDto);
		assertEquals(studentActual.getId(), ID_STUDENT);
		PersonDto studentActual1 = collegeService.addStudent(studentDto1);
		assertEquals(studentActual1.getName(), "Yosef");
		assertThrows(Exception.class, () -> collegeService.addStudent(studentDto));
	
	}
	
	
	
	@Test
	@Order(3)
	void addSubjectTest() {
		SubjectDto  subjectActual = collegeService.addSubject(subjectDto);
		assertEquals(subjectDto.getId(), subjectActual.getId());
		SubjectDto subjectActual1 = collegeService.addSubject(subjectDto1);
		assertEquals(subjectDto1.getId(), subjectActual1.getId());
		assertThrowsExactly(NotFoundException.class, () -> collegeService.addSubject(subjectDto2));
		assertThrowsExactly(IllegalStateException.class, () -> collegeService.addSubject(subjectDto3));
	}


	@Test
	@Order(4)
	void addMarkTest() {
		MarkDto markDto = new MarkDto(null, ID_STUDENT, subjectDto.getId(), 100);
		MarkDto markDtoNoStudent = new MarkDto(null, ID_STUDENT+10, subjectDto.getId(), 100);
		MarkDto markDtoNoSubject = new MarkDto(null, ID_STUDENT, "XXX", 100);
		MarkDto markDtoActual = collegeService.addMark(markDto);
		assertEquals(markDtoActual.getId(), 1);
		assertThrowsExactly(NotFoundException.class, () -> 
		collegeService.addMark(markDtoNoStudent));
		assertThrowsExactly(NotFoundException.class, () -> 
		collegeService.addMark(markDtoNoSubject));
	}
	
	

	
	
}
