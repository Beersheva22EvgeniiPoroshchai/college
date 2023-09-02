package telran.spring.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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


	@Query("select student.id as id, student.name as name from Mark group by id "     //by Class
			+ " having count(mark) > :nMarks and avg(mark) > (select avg (mark) from Mark) order by avg(mark) desc")
	List<IdName> findStudentsAvgMarksGreaterCollegeAvg(int nMarks);
	
	
	@Query(value="select sl.id as id, sl.name as name, coalesce(round(avg(mark)),0) as mark "
			+ " from (select * from students_lecturers where dtype='Student') sl"
			+ " left join marks on sl.id=student_id group by sl.id order by avg (mark) desc", nativeQuery = true)
	List<StudentMark>findStudentsAvgMarks();
	
	@Modifying
	@Query("delete from Student where size(marks) < :nMarks")
	void removeStudentsLessMark(int nMarks);
	
	@Query(value="select * from students_lecturers where dtype = 'Student' and"
			+ " id  in (select sl.id from students_lecturers sl left join marks on sl.id=student_id group by sl.id "
			+ "having count(mark) < :nMarks)", nativeQuery=true)
	List<Student> findStudentsLessMark(int nMarks);
	
	 //jpql: we work with Class, not table (group by and aggreg functs not support)
	@Query("select stud from Student stud where size(marks) < :nMarks")  
	List<Student> findStudentsLessMarks(int nMarks);
	
	
	}	




