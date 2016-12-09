package ru.innopolis.common.services;

import ru.innopolis.common.models.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 08.12.2016.
 */
public interface IStudentService {
    List<Student> getStudents() throws SQLException;
    String getName(int id) throws SQLException;
    int addStudent(String fio, String sex, String group) throws SQLException;
    int addStudentToLection(int lection_id, int student_id) throws SQLException;
    int removeStudentFromLection(int id) throws SQLException;
    int removeStudent(int id) throws SQLException;
    int updateStudent(int id, String fio, String sex, String group) throws SQLException;
}
