package ru.innopolis;

import ru.innopolis.models.ELection;
import ru.innopolis.models.Lection;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 08.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emfactory;
        EntityManager entityManager;
        emfactory = Persistence.createEntityManagerFactory("postgres");
        entityManager = emfactory.createEntityManager();
        //entityManager.getTransaction().begin();
        TypedQuery<ELection> query = entityManager.createQuery("from ru.innopolis.models.ELection", ru.innopolis.models.ELection.class);
        List<Lection> lections = new LinkedList<>();

        for (ELection l: query.getResultList()) {
            lections.add(new Lection(l.getId(), l.getDate(), l.getTheme()));
        }
    }
}
