package ru.innopolis.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.innopolis.common.models.Lection;
import ru.innopolis.common.models.Student;
import ru.innopolis.server.models.ELection;
import ru.innopolis.server.services.LectionService;
import ru.innopolis.server.services.StudentService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 08.12.2016.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"});

        //LectionService l = (LectionService)applicationContext.getBean("lectionService");
        StudentService s = (StudentService)applicationContext.getBean("studentService");
        //l.getLections();
        //s.getStudents();
        /*
        s.updateStudent(18,"111","men","GH-12");
        List<Student> sss =   s.getStudents();
        String name = s.getName(18);
        int newStudentId = s.addStudent("ddd","women","FDDg-12");

        int a = s.addStudentToLection(1,1);
        int b = s.removeStudent(22);
        int c = s.removeStudentFromLection(10);


        System.out.println("PKL");
        */

    }
}
