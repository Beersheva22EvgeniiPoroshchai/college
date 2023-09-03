package telran.spring.college.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.spring.college.dto.IdName;
import telran.spring.college.dto.MarkDto;
import telran.spring.college.dto.PersonDto;
import telran.spring.college.dto.QueryDto;
import telran.spring.college.dto.StudentMark;
import telran.spring.college.dto.SubjectDto;
import telran.spring.college.service.CollegeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("college")
public class CollegeController {
	final CollegeService collegeService;
	

	@PostMapping("/students")
	public PersonDto addStudent (@RequestBody PersonDto person) {
		return collegeService.addStudent(person);
	}
	
	@PostMapping("/lecturers")
	public PersonDto addLecturer (@RequestBody PersonDto person) {
		return collegeService.addLecturer(person);
	}
	
	
	@PostMapping("/subjects")
	public SubjectDto addSubject (@RequestBody SubjectDto subject) {
		return collegeService.addSubject(subject);
	}
	
	
	@PostMapping("/marks")
	public MarkDto addMark (@RequestBody MarkDto mark) {
		return collegeService.addMark(mark);
	}
	
	
	@GetMapping("/students/lecturer/best")
	public List <IdName> bestStudentsLecturer(@RequestParam long lecturerId, @RequestParam(defaultValue = "3") int nStudents) {
		return collegeService.bestStudentsLecturer(lecturerId, nStudents);
	}
	
	@GetMapping("/students/best")
	public List<IdName> studentsAvgMarksGreaterCollegeAvg(@RequestParam (name = "nMarks", defaultValue = "1") int nMarksThreshold) {
		return collegeService.studentsAvgMarksGreaterCollegeAvg(nMarksThreshold);
	}
	
	@GetMapping("/students/marks")
	public List<StudentMark> studentsAvgMarks() {
		return collegeService.studentsAvgMarks();
	}
	
	@PutMapping("/subjects/hours/{subjectId}")
	public SubjectDto updateHours(@PathVariable String subjectId, @RequestParam int hours) {
		return collegeService.updateHours(subjectId, hours);
	}
	
	@PutMapping("/subjects/lecturer/{subjectId}")
	public SubjectDto updateLecturer(@PathVariable String subjectId, @RequestParam Long lecturerId) {
		return collegeService.updateLecturer(subjectId, lecturerId);
	}
	
	
	@DeleteMapping("/students")
	public List<PersonDto> removeStudentsMarks(@RequestParam (defaultValue = "1") int nMarks) {
		return nMarks < 2 ? collegeService.removeStudentsNoMarks() : collegeService.removeStudentsLessMarks(nMarks);
	}
	
	
	@DeleteMapping("/lecturers/{lecturerId}")
	PersonDto removeLecturer(@PathVariable long lecturerId) {
		return collegeService.removeLecturer(lecturerId);
	}
	
	
	@PostMapping("/jpql")
	public List<String> jpqlQuery (@RequestBody QueryDto queryStr) {
		try {
			return collegeService.jpqlQuery(queryStr);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	
	
	
	
	
}
