package ru.innopolis.services;

import ru.innopolis.dao.PostgreSqlLectionDao;
import ru.innopolis.models.Lection;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */
public class LectionService {

    private PostgreSqlLectionDao postgreSqlLectionDao = new PostgreSqlLectionDao();

    public List<Lection> getLections() throws SQLException, NamingException {
        List<Lection> lections = postgreSqlLectionDao.getLections();
        return lections;
    }

    public List<Lection> getStudentLections(int id) throws SQLException, NamingException {
        if (id > 0) {
            List<Lection> lections = postgreSqlLectionDao.getStudentLections(id);
            return lections;
        }
        return null;
    }

    public int addLection(String date, String theme) throws SQLException, NamingException {
        if (date.length() > 0 && theme.length() > 0) {
            return postgreSqlLectionDao.addLection(date, theme);
        }

        return 0;
    }

    public int updateLection(int id, String date, String theme) throws SQLException, NamingException {
        if (id > 0 && date.length() > 0 && theme.length() > 0) {
            return postgreSqlLectionDao.updateLection(new Lection(id, date, theme));
        }

        return 0;
    }

    public int removeLection(int id) throws SQLException, NamingException {
        if (id > 0) {
            return postgreSqlLectionDao.removeLection(id);
        }

        return 0;
    }

    public List<Lection> getLectionsForStudent(int id) throws SQLException, NamingException {
        if (id > 0) {
            List<Lection> lections = postgreSqlLectionDao.getLectionsForStudent(id);
            return lections;
        }
        return null;
    }
}
