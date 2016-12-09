package ru.innopolis.services;

import ru.innopolis.dao.PostrgeSqlStudentDao;
import ru.innopolis.models.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */
public class StudentService {

    private PostrgeSqlStudentDao postrgeSqlStudentDao = new PostrgeSqlStudentDao();

    public List<Student> getStudents() throws SQLException, NamingException {
        List<Student> students = postrgeSqlStudentDao.getStudents();
        return students;
    }

    public String getName(int id) throws SQLException, NamingException {
        if (id > 0) {
            return postrgeSqlStudentDao.getName(id);
        }
        return null;
    }

    public int addStudent(String fio, String sex, String group) throws SQLException, NamingException {
        if (fio.length() > 0 && sex.length() > 0 && group.length() > 0) {
            return postrgeSqlStudentDao.addStudent(fio, sex, group);
        }
        return 0;
    }

    public int addStudentToLection(int lection_id, int student_id) throws SQLException, NamingException {
        if (lection_id > 0 && student_id > 0) {
            return postrgeSqlStudentDao.addStudentToLection(lection_id, student_id);
        }
        return 0;
    }

    public int removeStudentFromLection(int id) throws SQLException, NamingException {
        if (id > 0) {
            return postrgeSqlStudentDao.removeStudentFromLection(id);
        }
        return 0;
    }

    public int removeStudent(int id) throws SQLException, NamingException {
        if (id > 0) {
            return postrgeSqlStudentDao.removeStudent(id);
        }
        return 0;
    }

    public int updateStudent(int id, String fio, String sex, String group) throws SQLException, NamingException {
        if (id > 0 && fio.length() > 0 && sex.length() > 0 && group.length() > 0) {
            return postrgeSqlStudentDao.updateStudent(id, fio, sex, group);
        }
        return 0;
    }
}
