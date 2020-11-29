package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import service.DelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/DelServlet")
public class DelServlet extends HttpServlet {

    DelService delService = new DelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean trial = delService.del(id);
        if(trial) {
            result = new Result(true);
        } else {
            result = new Result(false);
        }
        out.print(mapper.writeValueAsString(result));
    }
}
