package telran.spring.college.service;

import java.util.*;

import telran.spring.college.dto.*;

public interface CollegeService {
	PersonDto addStudent(PersonDto person);
	PersonDto addLecturer(PersonDto person);
	SubjectDto addSubject(SubjectDto subject);
	MarkDto addMark(MarkDto mark);
	
	List <IdName> bestStudentsLecturer(long lecturerId, int nStudents);
	
	//list students (id and name) who have avg mark greater than avg mark of college and number marks not less than nMarksThreshold
	List<IdName> studentsAvgMarksGreaterCollegeAvg(int nMarksThreshold);
	
	//list students average mark
	List<StudentMark> studentsAvgMarks();
	
	
	SubjectDto updateHours(String subjectId, int hours);
	SubjectDto updateLecturer(String subjectId, Long lecturerId);
	List<PersonDto> removeStudentsNoMarks();
	List<PersonDto> removeStudentsLessMarks(int nMarks);

	
}
