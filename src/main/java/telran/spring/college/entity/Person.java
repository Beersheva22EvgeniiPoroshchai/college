package telran.spring.college.entity;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.spring.college.dto.PersonDto;

@Entity    //all instances of this class are to be persisted in a db table 
@NoArgsConstructor
@Data
@Table(name = "students_lecturers")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)    //separated tables
public class Person {
	
	@Id
	long id;
	String name;
	@Column(name = "birth_date")    //format for all types of database
  	@Temporal(TemporalType.DATE)     
	LocalDate birthDate;
	@Column(nullable = true)        //may be null
	String city;
	@Column(nullable = true)
	String phone;
	
	protected Person(PersonDto person) {
		id = person.getId();
		name = person.getName();
		birthDate = LocalDate.parse(person.getBirthDateStr());
		city = person.getCity();
		phone = person.getPhone();
	}
	
	public PersonDto build() {
		return new PersonDto(id, name, birthDate.toString(), city, phone);
	}
	
	
	}
