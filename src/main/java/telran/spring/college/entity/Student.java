package telran.spring.college.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)   //cascading remove by reverse relations in hibernate
	@OnDelete(action = OnDeleteAction.CASCADE)  //delete actually in data base 
	List<Mark> marks;
	
	}

