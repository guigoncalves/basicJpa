insert into Course (id, name, is_deleted) values (1001, 'Jpa in 5 Steps', false);
insert into Course (id, name, is_deleted) values (1002, 'Spring boot in 5 Steps', false);
insert into Course (id, name, is_deleted) values (1003, 'React Avancado', false);

insert into Passport (id, number) values (2001, 'E1234');
insert into Passport (id, number) values (2002, 'N4567');
insert into Passport (id, number) values (2003, 'G9876');

insert into Review (id, rate, description, course_id) values (3001, 'FIVE', 'Very Good', 1001);
insert into Review (id, rate, description, course_id) values (3002, 'THREE', 'Half Mouth', 1001);
insert into Review (id, rate, description, course_id) values (3003, 'ONE', 'Bad Course', 1002);


insert into Student (id, name, passport_id) values (4001, 'Guilherme', 2001);
insert into Student (id, name, passport_id) values (4002, 'Matheus', 2002);
insert into Student (id, name, passport_id) values (4003, 'Renan', 2003);

insert into student_course(student_id, course_id) values (4001,1001);
insert into student_course(student_id, course_id) values (4002,1001);
insert into student_course(student_id, course_id) values (4003,1001);
insert into student_course(student_id, course_id) values (4001,1003);
