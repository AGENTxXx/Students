package ru.innopolis.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.services.LectionService;
import ru.innopolis.services.StudentService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by Alexander Chuvashov on 25.11.2016.
 */
@Controller
public class StudentController {
    private StudentService studentService = new StudentService();
    private LectionService lectionService = new LectionService();

    @RequestMapping("/students")
    public String getAllStudents(Model model) throws SQLException, NamingException {
        model.addAttribute("students", studentService.getStudents());
        return "studentList";
    }
    @RequestMapping("/students/lections/{id}")
    public String getStudentLections(Model model, @PathVariable("id") int id) throws SQLException, NamingException {
        model.addAttribute("fio", studentService.getName(id));
        model.addAttribute("id", id);
        model.addAttribute("lections", lectionService.getStudentLections(id));
        return "lectStudList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }



    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value="/students/add", method = {RequestMethod.POST })
    public String addStudent(Model model,HttpServletRequest request) throws SQLException, NamingException {
        int user_id = studentService.addStudent(request.getParameter("fio"),request.getParameter("sex"),request.getParameter("group"));
        String result = "{\"success\" : true, \"method\":\"add\", \"user_id\":" + user_id + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/students/lection/add", method = {RequestMethod.POST })
    public String addStudentToLection(Model model,HttpServletRequest request) throws SQLException, NamingException {
        int id = studentService.addStudentToLection(Integer.parseInt(request.getParameter("lection_id")),Integer.parseInt(request.getParameter("student_id")));
        String result = "{\"success\" : true, \"method\":\"add\", \"id\":" + id + ", \"lection_id\":" + request.getParameter("lection_id") + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/students/lection/remove/{id}", method = {RequestMethod.POST })
    public String removeStudentFromLection(Model model, @PathVariable("id") int id) throws SQLException, NamingException {
        studentService.removeStudentFromLection(id);
        String result = "{\"success\" : true, \"method\":\"remove\", \"id\":" + id + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/students/remove/{id}", method = {RequestMethod.POST })
    public String removeStudent(Model model, @PathVariable("id") int id) throws SQLException, NamingException {
        studentService.removeStudent(id);
        String result = "{\"success\" : true, \"method\":\"remove\", \"user_id\":"+id+"}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/students/update/{id}", method = {RequestMethod.POST })
    public String updateStudent(Model model, @PathVariable("id") int id, HttpServletRequest request) throws SQLException, NamingException {
        studentService.updateStudent(id, request.getParameter("fio"), request.getParameter("sex"), request.getParameter("group"));
        String result = "{\"success\" : true, \"method\":\"update\", \"user_id\":" + id + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }
}
