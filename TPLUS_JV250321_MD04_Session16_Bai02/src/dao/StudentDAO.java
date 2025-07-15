package dao;

import entity.Student;

public interface StudentDAO {
    boolean save(Student student);

    boolean update(Student student);

}
