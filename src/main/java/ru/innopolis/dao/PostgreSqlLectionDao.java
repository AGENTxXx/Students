package ru.innopolis.dao;

import ru.innopolis.models.ELection;
import ru.innopolis.models.Lection;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class PostgreSqlLectionDao {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("postgres");
    EntityManager entityManager = emfactory.createEntityManager();

    public List<Lection> getLections() throws SQLException, NamingException {

        TypedQuery<ELection> query = entityManager.createQuery("from ELection", ELection.class);
        List<Lection> lections = new LinkedList<>();

        for (ELection l: query.getResultList()) {
            lections.add(new Lection(l.getId(), l.getDate(), l.getTheme()));
        }

        //List<Lection> lections = (List<Lection>)query.getResultList();
        return  lections;

        /*
        String sql = "SELECT id, date, theme FROM LECTIONS";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            List<Lection> lections = new LinkedList<Lection>();

            while (rs.next()) {
                lections.add(new Lection(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

            if (lections.size() > 0) {
                return lections;
            }
        }
        return null;
        */
    }
    public List<Lection> getStudentLections(int id) throws SQLException, NamingException {

        String sql = "SELECT ls.id, l.date, l.theme FROM LECTSTUD ls" +
                " LEFT JOIN LECTIONS l ON l.id = ls.lectionId WHERE ls.studentId = ?";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            List<Lection> lections = new LinkedList<Lection>();

            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                lections.add(new Lection(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return lections;
        }
    }

    public int addLection(String date, String theme) throws SQLException, NamingException {
        int id = 0;
        entityManager.getTransaction().begin();
        Lection lection = new Lection(date, theme);
        entityManager.persist(lection);
        entityManager.getTransaction().commit();

        id = lection.getId();
        entityManager.close( );
        emfactory.close( );

        return id;

        /*
        String sql = "INSERT INTO LECTIONS (date,theme) VALUES(?,?) RETURNING id";
        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1,date);
            stm.setString(2,theme);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        */
    }

    public int updateLection(Lection lection) throws SQLException, NamingException {

        entityManager.getTransaction().begin();
        entityManager.merge(lection);
        entityManager.getTransaction().commit();
        entityManager.close();
        emfactory.close();
        return 1;

        /*
        String sql = "UPDATE LECTIONS SET date = ?, theme = ? WHERE id= ?";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1,date);
            stm.setString(2,theme);
            stm.setInt(3,id);
            return stm.executeUpdate();
        }
        */
    }

    public int removeLection(int id) throws SQLException, NamingException {
        entityManager.getTransaction().begin();
        Lection employee=entityManager.find( Lection.class, id );
        entityManager.remove( employee );
        entityManager.getTransaction().commit();
        entityManager.close();
        emfactory.close();

        return 1;

        /*
        String sql = "DELETE FROM LECTIONS WHERE id = ?";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1,id);
            return stm.executeUpdate();
        }
        */
    }

    public List<Lection> getLectionsForStudent(int id) throws SQLException, NamingException {


        //entityManager.getCriteriaBuilder().createQuery("from SecureMessage sm where sm.someField=:arg1");
        TypedQuery<ELection> query = entityManager.createQuery("from ELection", ELection.class);
        List<Lection> lections = new LinkedList<>();

        for (ELection l: query.getResultList()) {
            lections.add(new Lection(l.getId(), l.getDate(), l.getTheme()));
        }

        //List<Lection> lections = (List<Lection>)query.getResultList();
        return  lections;
        /*
        String sql = "SELECT l.id, l.date, l.theme FROM LECTIONS l" +
        " WHERE l.id NOT IN (SELECT lection_id  FROM lectstud  WHERE student_id = ?)";

        try(Connection con = DB.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            List<Lection> lections = new LinkedList<Lection>();

            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                lections.add(new Lection(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return lections;
        }
        */
    }
}
