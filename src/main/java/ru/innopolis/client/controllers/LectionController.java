package ru.innopolis.client.controllers;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.models.Lection;
import ru.innopolis.services.LectionService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexander Chuvashov on 25.11.2016.
 */
@Controller
public class LectionController {
    private LectionService lectionService = new LectionService();

    @RequestMapping("/lections")
    public String getAllLections(Model model) throws SQLException, NamingException {
        model.addAttribute("lections", lectionService.getLections());
        return "lectionList";
    }
    @RequestMapping("/lections/forstudent/{id}")
    public String getAllLectionsJson(Model model, @PathVariable("id") int id) throws SQLException, NamingException {
        List<Lection> lectionList = lectionService.getLectionsForStudent(id);
        Gson gson = new Gson();
        String result = "{\"success\" : true, \"method\":\"getLections\", \"lections\":" + gson.toJson(lectionList) + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/lections/add")
    public String addLection(Model model,HttpServletRequest request) throws SQLException, NamingException {
        int lection_id = lectionService.addLection(request.getParameter("date"),request.getParameter("theme"));
        String result = "{\"success\" : true, \"method\":\"add\", \"lection_id\":" + lection_id + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/lections/remove/{id}", method = {RequestMethod.POST })
    public String removeLection(Model model, @PathVariable("id") int id) throws SQLException, NamingException {
        lectionService.removeLection(id);
        String result = "{\"success\" : true, \"method\":\"remove\", \"lection_id\":"+id+"}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

    @RequestMapping(value="/lections/update/{id}", method = {RequestMethod.POST })
    public String updateLection(Model model, @PathVariable("id") int id, HttpServletRequest request) throws SQLException, NamingException {
        lectionService.updateLection(id, request.getParameter("date"), request.getParameter("theme"));
        String result = "{\"success\" : true, \"method\":\"update\", \"lection_id\":" + id + "}";
        model.addAttribute("result",result);
        return "ajaxResult";
    }

}
