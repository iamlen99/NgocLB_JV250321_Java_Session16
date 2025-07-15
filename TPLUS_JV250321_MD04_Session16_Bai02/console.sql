create schema student_db;
use student_db;

DELIMITER $$
create procedure update_student(
    id_in int,
    name_in varchar(50),
    age_in int
)
begin
    update students
    set name = name_in,
        age  = age_in
    where id = id_in;
end $$
DELIMITER ;



