package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import org.apache.commons.beanutils.BeanUtils;
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
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

@WebServlet("/servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    MemberService memberService = new MemberService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String[]> pm =  request.getParameterMap();
        Member member = new Member();
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        try {
            BeanUtils.populate(member, pm);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        member.setRtime(new Date());
        boolean result1 = memberService.register(member);
        if (result1 == true) {
            result = new Result(true, "success");
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        } else {
            result = new Result(false, "phone already registered");
        }
        out.print(mapper.writeValueAsString(result));
    }
}
