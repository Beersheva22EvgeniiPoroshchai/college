package telran.spring.college.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import telran.spring.college.dto.PersonDto;

@Entity
//@Table (name = "lectureres")

@NoArgsConstructor
public class Lecturer extends Person {



	private Lecturer(PersonDto person) {
	super(person);
	// TODO Auto-generated constructor stub
	}

	public static Lecturer of(PersonDto person) {
	//possible addition validation
	return new Lecturer(person);
	}
	
	
//	@OneToMany(mappedBy = "lecturer")
//	@OnDelete(action = OnDeleteAction.SET_NULL)
//	List<Subject> subjects;
	
	
}
