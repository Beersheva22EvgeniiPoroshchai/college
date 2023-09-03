package telran.spring.college;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import telran.spring.college.dto.*;
import telran.spring.college.entity.*;
import telran.spring.college.repo.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.college.service.CollegeService;
@SpringBootTest
@Sql(scripts = {"college-read-test-script.sql"})
class CollegeServiceReadTest {
	@Autowired    //link to the object
	CollegeService collegeService;
	@Autowired
	SubjectRepositary subjectRepositary;
	@Autowired
	StudentRepositary studentRepositary;

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
	@Transactional
	void fetchLecturerTest() {
		Subject subject = subjectRepositary.findById("S3").get();
		assertEquals(421, subject.getLecturer().getId());
		assertEquals("branda walles", subject.getLecturer().getName());
		
	}

	@Test
	@Transactional
	void fetchMarksTest() {
		Student student = studentRepositary.findById(123L).get();
		assertEquals(4, student.getMarks().size());
		
	}
	
	
	@Test
	void fetchLecturerNoTransactionalTest() {
		Subject subject = subjectRepositary.findById("S3").get();
		assertThrows(Exception.class, () ->  subject.getLecturer().getName());
		
	}

	@Test
	void fetchMarksNoTransactionalTest() {
		Student student = studentRepositary.findById(123L).get();
		assertThrows(Exception.class, () -> student.getMarks().size());
		
	}
	
	
	@Test
	void jpqlSingleProjectionTest() {
		String query = "select id from Student order by id";
		List<String> res = collegeService.jpqlQuery(new QueryDto(query, 5));
		assertEquals(5, res.size());
		String[] expected = {"123", "124", "125", "126", "127"};
		assertArrayEquals(expected, res.toArray(String[]::new));
	
	}
	
	
	@Test
	void jpqlMultyProjectionTest() {
		String query = "select id, name from Student order by id";
		List<String> res = collegeService.jpqlQuery(new QueryDto(query, null) );
		assertEquals(5, res.size());
		String[] expected = {"[123, vasya]", "[124, sara]", "[125, josef]", "[126, david]", "[127, rivka]"};
		assertArrayEquals(expected, res.toArray(String[]::new));
	
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
