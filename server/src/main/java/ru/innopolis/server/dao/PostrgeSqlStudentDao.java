package ru.innopolis.server.dao;

import ru.innopolis.common.models.Student;
import ru.innopolis.server.models.ELectstud;
import ru.innopolis.server.models.EStudent;

import javax.persistence.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class PostrgeSqlStudentDao {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("postgres");

    public List<Student> getStudents() throws SQLException {

        EntityManager entityManager = emfactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT s.id, s.fio, s.sex, s.groupNum, COUNT(l.id) as lectionCount " +
                "FROM ELectstud l " +
                "RIGHT JOIN EStudent s ON s.id = l.studentid " +
                "GROUP BY s.id");

        List<Object[]> result = query.getResultList();

        List<Student> students = new LinkedList<>();

        for (Object[] f: result) {
            students.add(new Student((int)f[0], f[1].toString(), f[2].toString(), f[3].toString(), (long)f[4]));//s.getLectionCount()
        }
        entityManager.close();
        return students;
    }

    public String getName(int id) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();

        EStudent student =entityManager.find( EStudent.class, id );
        entityManager.close();

        return student.getFio();
    }

    public int addStudent(String fio, String sex, String group) throws SQLException {

        EntityManager entityManager = emfactory.createEntityManager();

        int id = 0;
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
        EStudent student = new EStudent(fio, sex, group);
        entityManager.persist(student);
        tx.commit();

        id = student.getId();
        entityManager.close( );

        return id;
    }

    public int addStudentToLection(int lectionId, int studentId) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        int id = 0;
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
        ELectstud lectstud = new ELectstud(lectionId,studentId);
        entityManager.persist(lectstud);
        tx.commit();

        id = lectstud.getId();
        entityManager.close( );

        return id;
    }

    public int removeStudentFromLection(int id) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
        ELectstud electstud=entityManager.find( ELectstud.class, id );
        entityManager.remove( electstud );
        tx.commit();
        entityManager.close();

        return 1;

    }

    public int removeStudent(int id) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }

        EStudent student=entityManager.find( EStudent.class, id );
        entityManager.remove( student );
        tx.commit();
        entityManager.close();
        return 1;
    }

    public int updateStudent(int id, String fio, String sex, String group) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }

        EStudent student = entityManager.find(EStudent.class,id);
        student.setFio(fio);
        student.setSex(sex);
        student.setGroupNum(group);
        entityManager.merge(student);
        tx.commit();
        entityManager.close();
        return 1;
    }
}
