delete from marks;
delete from subjects;
delete from students_lecturers;
insert into students_lecturers (id, name, birth_date, dtype) values (123, 'vasya', '2000-01-01', 'Student');
insert into students_lecturers (id, name, birth_date, dtype) values (124, 'sara', '2004-03-07', 'Student');
insert into students_lecturers (id, name, birth_date, dtype) values (125, 'josef', '2002-02-09', 'Student');
insert into students_lecturers (id, name, birth_date, dtype) values (126, 'david', '2001-09-11', 'Student');
insert into students_lecturers (id, name, birth_date, dtype) values (127, 'rivka', '2003-06-04', 'Student');
insert into students_lecturers (id, name, birth_date, dtype) values (321, 'yuri granovski', '1950-06-06', 'Lecturer');
insert into students_lecturers (id, name, birth_date, dtype) values (421, 'branda walles', '2001-05-10', 'Lecturer');
insert into subjects (id, name, hours, type, lecturer_id) values('S1', 'Java Core', 100, 'BACK_END', 321);
insert into subjects (id, name, hours, type, lecturer_id) values('S2', 'Java Technologies', 100, 'BACK_END', 321);
insert into subjects (id, name, hours, type, lecturer_id) values('S3', 'JS', 100, 'FRONT_END', 421);
insert into subjects (id, name, hours, type, lecturer_id) values('S4', 'React', 100, 'FRONT_END', 421);
insert into marks (student_id, subject_id, mark) values (123, 'S1', 80);
insert into marks (student_id, subject_id, mark) values (123, 'S2', 100);
insert into marks (student_id, subject_id, mark) values (123, 'S3', 90);
insert into marks (student_id, subject_id, mark) values (123, 'S4', 90);
insert into marks (student_id, subject_id, mark) values (127, 'S1', 100);
insert into marks (student_id, subject_id, mark) values (127, 'S2', 100);
insert into marks (student_id, subject_id, mark) values (127, 'S3', 100);
insert into marks (student_id, subject_id, mark) values (127, 'S4', 100);
insert into marks (student_id, subject_id, mark) values (125, 'S1', 80);
insert into marks (student_id, subject_id, mark) values (125, 'S2', 80);
insert into marks (student_id, subject_id, mark) values (125, 'S3', 80);
insert into marks (student_id, subject_id, mark) values (124, 'S2', 75);
insert into marks (student_id, subject_id, mark) values (124, 'S3', 75);





