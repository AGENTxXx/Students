package ru.innopolis.common.services;

import ru.innopolis.common.models.Lection;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 08.12.2016.
 */
public interface ILectionService {
    List<Lection> getLections() throws SQLException;
    List<Lection> getStudentLections(int id) throws SQLException;
    int addLection(String date, String theme) throws SQLException;
    int updateLection(Lection lection) throws SQLException;
    int removeLection(int id) throws SQLException;
    List<Lection> getLectionsForStudent(int id) throws SQLException;
}
