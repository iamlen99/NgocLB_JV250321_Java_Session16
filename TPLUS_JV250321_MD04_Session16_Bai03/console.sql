create schema student_db;
use student_db;

DELIMITER $$
create procedure delete_students_by_age(
    age_in int
)
begin
delete from students
    where age < age_in;
end $$
DELIMITER ;

