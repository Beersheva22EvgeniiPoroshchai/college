package telran.spring.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.spring.college.dto.*;
import telran.spring.college.entity.*;

public interface StudentRepositary extends JpaRepository<Student, Long> {

	@Query(value="select sl.id as id, sl.name as name from (select * from students_lecturers where dtype='Student') sl "
			+ "join marks on sl.id=student_id join subjects sbj on subject_id=sbj.id where lecturer_id=:lecturerId "
		 	+ "group by sl.id order by avg(mark) desc limit :nStudents"
			, nativeQuery = true)
	List<IdName> findbestStudentsLecturer(@Param(value = "lecturerId") long lecturerId, int nStudents);


	@Query(value="select sl.id as id, sl.name as name from (select * from students_lecturers where dtype='Student') sl"
			+ " join marks on sl.id=student_id join subjects sbj on subject_id=sbj.id group by sl.id"
			+ " having count(mark) > :nMarksThreshold and avg(mark) > (select avg(mark) from marks) order by avg (mark) desc ",
			nativeQuery = true)
	List<IdName> findStudentsAvgMarksGreaterCollegeAvg(int nMarksThreshold);
	
	
	@Query(value="select sl.id as id, sl.name as name, coalesce(round(avg(mark)),0) as mark "
			+ " from (select * from students_lecturers where dtype='Student') sl"
			+ " left join marks on sl.id=student_id group by sl.id order by avg (mark) desc", nativeQuery = true)
	List<StudentMark>findStudentsAvgMarks();
	
	@Query(value="select * from students_lecturers where dtype = 'Student' "
			+ " and id in (select sl.id from students_lecturers sl left join marks on sl.id=student_id group by sl.id "
			+ " having count(mark) < :nMarks)",
			nativeQuery = true)
	List<Student>findStudentsLessMark(int nMarks);
	
	
	
	

}




