package ru.innopolis.dao;

import ru.innopolis.models.Student;

import javax.naming.NamingException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class PostrgeSqlStudentDao {

    public List<Student> getStudents() throws SQLException, NamingException {
        String sql = "SELECT s.id, s.fio, s.sex, s.groupNum, COUNT(l.id) lection_count FROM LECTSTUD l " +
                    "RIGHT JOIN STUDENTS s ON s.id = l.studentId " +
                    "GROUP BY s.id";
        Connection con = DB.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Student> students = new LinkedList<Student>();

        while(rs.next()) {
            students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
        }
        return students;
    }

    public String getName(int id) throws SQLException, NamingException {
        String sql = "SELECT fio FROM STUDENTS WHERE id = ?";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            String fio = rs.getString(1);
            return fio;
        }
    }

    public int addStudent(String fio, String sex, String group) throws SQLException, NamingException {
        String sql = "INSERT INTO STUDENTS (fio,sex,groupNum) VALUES (?,?,?) RETURNING id";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1,fio);
            stm.setString(2,sex);
            stm.setString(3,group);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    public int addStudentToLection(int lection_id, int student_id) throws SQLException, NamingException {
        String sql = "INSERT INTO LECTSTUD (lectionId,studentId) VALUES (?,?) RETURNING id";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1,lection_id);
            stm.setInt(2,student_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);
        }

    }

    public int removeStudentFromLection(int id) throws SQLException, NamingException {
        String sql = "DELETE FROM LECTSTUD WHERE id = ?";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1,id);
            return stm.executeUpdate();
        }
    }

    public int removeStudent(int id) throws SQLException, NamingException {
        String sql = "DELETE FROM STUDENTS WHERE id = ?";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1,id);
            return stm.executeUpdate();
        }
    }

    public int updateStudent(int id, String fio, String sex, String group) throws SQLException, NamingException {
        String sql = "UPDATE STUDENTS SET fio = ?, sex = ?, groupNum = ? WHERE id= ?";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1,fio);
            stm.setString(2,sex);
            stm.setString(3,group);
            stm.setInt(4,id);
            return stm.executeUpdate();
        }
    }
}
