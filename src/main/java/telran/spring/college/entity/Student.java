package telran.spring.college.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.spring.college.dto.PersonDto;

@Entity
//@Table(name = "students")

//@NoArgsConstructor
public class Student extends Person {
	public Student() {
		
	}
	
	private Student(PersonDto person) {
		super(person);
	}
	
	public static Student of(PersonDto person) {
		return new Student(person);
	}
	
	@Getter
	@OneToMany(mappedBy = "student")   //cascading remove by reverse relations in hibernate
	//@OnDelete(action = OnDeleteAction.CASCADE)  //delete actually in data base 
	List<Mark> marks;

	
	}

