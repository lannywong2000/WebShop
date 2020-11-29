package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Admin;
import service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/AdminServlet")
public class AdminServlet extends HttpServlet {

    AdminService adminService = new AdminService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        Admin admin = adminService.login(phone);
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        if (admin != null) {
            if (admin.getPwd().equals(pwd)) {
                //登录成功后将管理员信息保存到session中
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                result = new Result(true, "success");
            } else {
                result = new Result(true, "pwd not match");
            }
        } else {
            result = new Result(false, "no user");
        }
        out.print(mapper.writeValueAsString(result));
    }
}
