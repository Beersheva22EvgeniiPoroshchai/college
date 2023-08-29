package telran.spring.college.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telran.spring.college.dto.*;

@Entity
@Table(name="subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class Subject {
	@Id
	String id;
	String name;
	int hours;
	@Enumerated(EnumType.STRING)
	SubjectType type;
	@ManyToOne    //many subjects to one lecture
	@JoinColumn(name = "lecturer_id", nullable = true)
	Lecturer lecturer;
	public SubjectDto build() {
		return new SubjectDto(id, name, hours, lecturer == null ? -1 : lecturer.id, type);
	}
	
	public static Subject of (SubjectDto subject) {
		return new Subject(subject.getId(), subject.getName(), subject.getHours(), subject.getSubjectType(), null);
	}
}
