package telran.spring.college.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import telran.spring.college.dto.PersonDto;

@Entity
//@Table(name = "students")

@NoArgsConstructor
public class Student extends Person {
	
	
	private Student(PersonDto person) {
		super(person);
	}
	
	public static Student of(PersonDto person) {
		return new Student(person);
	}

	
	
}
