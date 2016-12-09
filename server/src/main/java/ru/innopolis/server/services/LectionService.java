package ru.innopolis.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.innopolis.common.services.ILectionService;
import ru.innopolis.server.dao.PostgreSqlLectionDao;
import ru.innopolis.common.models.Lection;
import ru.innopolis.server.models.ELection;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 01.12.2016.
 */
public class LectionService implements ILectionService {

    private PostgreSqlLectionDao postgreSqlLectionDao;

    public LectionService() {
        this.postgreSqlLectionDao = new PostgreSqlLectionDao();
    }

    /*
    @Autowired(required = true)
    @Qualifier(value = "postgreSqlLectionDao")
    public void setPostgreSqlLectionDao(ILectionService lectionService) {
        this.lectionService = lectionService;
    }
    */

    public List<Lection> getLections() throws SQLException {
        List<Lection> lections = postgreSqlLectionDao.getLections();
        return lections;
    }

    public Lection getLection(int id) throws SQLException {
        return postgreSqlLectionDao.getLection(id);
    }

    public List<Lection> getStudentLections(int id) throws SQLException {
        if (id > 0) {
            List<Lection> lections = postgreSqlLectionDao.getStudentLections(id);
            return lections;
        }
        return null;
    }

    public int addLection(String date, String theme) throws SQLException {
        if (date.length() > 0 && theme.length() > 0) {
            return postgreSqlLectionDao.addLection(date, theme);
        }

        return 0;
    }

    public int updateLection(Lection lection) throws SQLException {
        if (lection.getId() > 0 && lection.getDate().length() > 0 && lection.getTheme().length() > 0) {
            return postgreSqlLectionDao.updateLection(new ELection(lection.getId(), lection.getDate(), lection.getTheme()));
        }

        return 0;
    }

    public int removeLection(int id) throws SQLException {
        if (id > 0) {
            return postgreSqlLectionDao.removeLection(id);
        }

        return 0;
    }

    public List<Lection> getLectionsForStudent(int id) throws SQLException {
        if (id > 0) {
            List<Lection> lections = postgreSqlLectionDao.getLectionsForStudent(id);
            return lections;
        }
        return null;
    }
}
