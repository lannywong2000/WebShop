package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Member;
import service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {

    MemberService memberService = new MemberService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        Member member = memberService.login(phone);
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        if (member != null) {
            if (member.getPwd().equals(pwd)) {
                //登录成功后将用户信息保存到session中
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
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
