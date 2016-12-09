package ru.innopolis.server.dao;

import ru.innopolis.server.models.ELection;
import ru.innopolis.common.models.Lection;

import javax.naming.NamingException;
import javax.persistence.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 24.11.2016.
 */
public class PostgreSqlLectionDao {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("postgres");

    public List<Lection> getLections() throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<ELection> query = entityManager.createQuery("from ELection", ELection.class);
        List<Lection> lections = new LinkedList<>();

        for (ELection l: query.getResultList()) {
            lections.add(new Lection(l.getId(), l.getDate(), l.getTheme()));
        }
        entityManager.close( );

        return  lections;
    }
    public List<Lection> getStudentLections(int id) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT ls.id as lectstudid, l.id, l.date, l.theme FROM ELectstud ls" +
                        " LEFT JOIN ELection l ON l.id = ls.lectionid WHERE ls.studentid = ?1");

        query.setParameter(1, id);
        List<Object[]> result = query.getResultList();

        List<Lection> lections = new LinkedList<>();

        for (Object[] l: result) {
            lections.add(new Lection((int)l[0], (int)l[1], l[2].toString(),l[3].toString()));
        }
        entityManager.close( );

        return lections;
    }

    public int addLection(String date, String theme) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();
        int id = 0;
        entityManager.getTransaction().begin();
        ELection lection = new ELection(date, theme);
        entityManager.persist(lection);
        entityManager.getTransaction().commit();

        id = lection.getId();
        entityManager.close( );

        return id;
    }

    public int updateLection(ELection lection) throws SQLException {

        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(lection);
        entityManager.getTransaction().commit();
        entityManager.close();
        return 1;

    }

    public int removeLection(int id) throws SQLException {
        EntityManager entityManager = emfactory.createEntityManager();

        entityManager.getTransaction().begin();
        ELection employee=entityManager.find( ELection.class, id );
        entityManager.remove( employee );
        entityManager.getTransaction().commit();
        entityManager.close();

        return 1;
    }

    public List<Lection> getLectionsForStudent(int id) throws SQLException {

        EntityManager entityManager = emfactory.createEntityManager();

        TypedQuery<ELection> query = entityManager.createQuery("from ELection", ELection.class);
        List<Lection> lections = new LinkedList<>();

        for (ELection l: query.getResultList()) {
            lections.add(new Lection(l.getId(), l.getDate(), l.getTheme()));
        }
        entityManager.close();

        return  lections;

    }

    public Lection getLection(int id) {
        EntityManager entityManager = emfactory.createEntityManager();

        ELection result = entityManager.find(ELection.class, id);
        entityManager.close();

        return new Lection(result.getId(), result.getDate(), result.getTheme());
    }
}
