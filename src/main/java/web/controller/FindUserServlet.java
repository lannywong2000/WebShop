package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("member");
        result = new Result(true, member, "");
        out.print(mapper.writeValueAsString(result));
    }
}
