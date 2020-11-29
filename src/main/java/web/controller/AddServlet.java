package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import service.AddService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/AddServlet")
public class AddServlet extends HttpServlet {

    AddService addService = new AddService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(request.getParameter("id"));
        addService.add(id);
        result = new Result(true);
        out.print(mapper.writeValueAsString(result));
    }
}
