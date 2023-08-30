package telran.spring.college;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import telran.spring.college.dto.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.spring.college.service.CollegeService;
@SpringBootTest
@Sql(scripts = {"college-read-test-script.sql"})
class CollegeServiceReadTest {
	@Autowired    //link to the object
	CollegeService collegeService;

	@Test
	void bestStudentsLecturerTest() {
		List<IdName> actual = collegeService.bestStudentsLecturer(321, 2);
		assertEquals(2, actual.size());
		assertEquals(127, actual.get(0).getId());
		assertEquals("rivka", actual.get(0).getName());
		assertEquals(123, actual.get(1).getId());
		assertEquals("vasya", actual.get(1).getName());
		
	}
	
	@Test
	void findStudentsAvgMarksGreaterCollegeAvgTest() {
	List<IdName> actual = collegeService.studentsAvgMarksGreaterCollegeAvg(2);
	assertEquals(actual.size(), 2);
	assertEquals(127, actual.get(0).getId());
	assertEquals("rivka", actual.get(0).getName());
	assertEquals(123, actual.get(1).getId());
	assertEquals("vasya", actual.get(1).getName());
	
	}
	
	@Test
	void findStudentsAvgMarksTest() {
	List<StudentMark> actual = collegeService.studentsAvgMarks();
	assertEquals(5, actual.size());
	assertEquals(127, actual.get(0).getId());
	assertEquals(123, actual.get(1).getId());
	assertEquals("rivka", actual.get(0).getName());
	assertEquals("vasya", actual.get(1).getName());
	assertEquals("josef", actual.get(2).getName());
	assertEquals("sara", actual.get(3).getName());
	assertEquals("david", actual.get(4).getName());
	assertEquals(126, actual.get(4).getId());
	assertEquals(100,actual.get(0).getMark());
	assertEquals(90,actual.get(1).getMark());
	assertEquals(80,actual.get(2).getMark());
	assertEquals(75,actual.get(3).getMark());
	assertEquals(0,actual.get(4).getMark());
 
	}
	
	@Test
	void marksStudSubjTest() {
		List<MarkDto> marks = collegeService.marksStudentSubject(124, "S2");
		assertEquals(75, marks.get(0).getMark());
		assertEquals(1, marks.size());
	}
	
	@Test
	void studentMarksSubjTest() {
		List<IdName> students = collegeService.studentMarksSubject(SubjectType.BACK_END, 90);
		assertEquals("vasya", students.get(0).getName());
		assertEquals("rivka", students.get(1).getName());
		assertEquals(2, students.size());
		
		
		
	}
	
	
	
	
}
