package telran.spring.college;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.college.dto.PersonDto;
import telran.spring.college.entity.Subject;
import telran.spring.college.repo.StudentRepositary;
import telran.spring.college.repo.SubjectRepositary;
import telran.spring.college.service.CollegeService;
import telran.spring.exceptions.NotFoundException;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollegeServiceUpdateDeleteTest {
	static final String SUBJECT_ID = "S1";
	static final Long LECTURER_ID = 421L;
	static final int HOURS = 200;
	private static final Long STUDENT_REMOVED_ID_0 = 126L;
	private static final Long STUDENT_REMOVED_ID_1 = 124L;
@Autowired
CollegeService collegeCervice;
@Autowired
SubjectRepositary subjectRepositary;
@Autowired
StudentRepositary studentRepositary;

	
	@Test
	@Order(1)
	@Sql(scripts = {"college-read-test-script.sql"})
	void updateHours() {
		collegeCervice.updateHours(SUBJECT_ID, HOURS);
		assertThrowsExactly(NotFoundException.class, () -> collegeCervice.updateHours(SUBJECT_ID+10, HOURS));
		
	}
	
	
	@Test
	@Order(3)
	@Sql(scripts = {"college-read-test-script.sql"})
	void updateLecturer() {
		collegeCervice.updateLecturer(SUBJECT_ID, LECTURER_ID);
		assertThrowsExactly(NotFoundException.class, () -> collegeCervice.updateLecturer(SUBJECT_ID+10, LECTURER_ID));
		assertThrowsExactly(NotFoundException.class, () -> collegeCervice.updateLecturer(SUBJECT_ID, LECTURER_ID+10));
	}
	
	
	@Test
	@Order(2)
	@Transactional(readOnly = true)
	void updateHoursTest() {
		Subject subject = subjectRepositary.findById(SUBJECT_ID).get();
		assertEquals(HOURS, subject.getHours());
	}
	
	
	@Test
	@Order(4)
	@Transactional(readOnly = true)
	void updateLecturerTest() {
		Subject subject = subjectRepositary.findById(SUBJECT_ID).get();
		assertEquals(LECTURER_ID, subject.getLecturer().getId());
	}
	
	
	@Test
	@Order(5)
	//@Transactional(readOnly = true)
	void removeStudentNoMark() {
		List<PersonDto> studentsRemoved = collegeCervice.removeStudentsNoMarks();
		assertEquals(1, studentsRemoved.size());
	}
	
	
	@Test
	@Order(6)
	@Transactional(readOnly = true)
	void removeStudentNoMarkTest() {
		assertNull(studentRepositary.findById(STUDENT_REMOVED_ID_0).orElse(null));
	}
	
	@Test
	@Order(7)
	@Sql(scripts = {"college-read-test-script.sql"})
	void removeStudentsLessMark() {
		List<PersonDto> studentsRemoved = collegeCervice.removeStudentsLessMarks(3);
		assertEquals(2, studentsRemoved.size());
	}
	
	@Test
	@Order(8)
	@Transactional(readOnly = true)
	void removeStudentsLessMarkTest() {
		assertNull(studentRepositary.findById(STUDENT_REMOVED_ID_0).orElse(null));
		assertNull(studentRepositary.findById(STUDENT_REMOVED_ID_1).orElse(null));
		
	}
	
	
	
	
}
