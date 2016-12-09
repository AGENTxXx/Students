package ru.innopolis.server.services;

import ru.innopolis.common.services.IStudentService;
import ru.innopolis.server.dao.PostrgeSqlStudentDao;
import ru.innopolis.common.models.Student;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */
public class StudentService implements IStudentService {

    private PostrgeSqlStudentDao postrgeSqlStudentDao;

    public StudentService() {
        this.postrgeSqlStudentDao = new PostrgeSqlStudentDao();
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = postrgeSqlStudentDao.getStudents();
        return students;
    }

    public String getName(int id) throws SQLException {
        if (id > 0) {
            return postrgeSqlStudentDao.getName(id);
        }
        return null;
    }

    public int addStudent(String fio, String sex, String group) throws SQLException {
        if (fio.length() > 0 && sex.length() > 0 && group.length() > 0) {
            return postrgeSqlStudentDao.addStudent(fio, sex, group);
        }
        return 0;
    }

    public int addStudentToLection(int lection_id, int student_id) throws SQLException {
        if (lection_id > 0 && student_id > 0) {
            return postrgeSqlStudentDao.addStudentToLection(lection_id, student_id);
        }
        return 0;
    }

    public int removeStudentFromLection(int id) throws SQLException {
        if (id > 0) {
            return postrgeSqlStudentDao.removeStudentFromLection(id);
        }
        return 0;
    }

    public int removeStudent(int id) throws SQLException {
        if (id > 0) {
            return postrgeSqlStudentDao.removeStudent(id);
        }
        return 0;
    }

    public int updateStudent(int id, String fio, String sex, String group) throws SQLException {
        if (id > 0 && fio.length() > 0 && sex.length() > 0 && group.length() > 0) {
            return postrgeSqlStudentDao.updateStudent(id, fio, sex, group);
        }
        return 0;
    }
}
