package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/DelCartServlet")
public class DelCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        int id = Integer.parseInt(request.getParameter("id"));
        Cart cart = (Cart)session.getAttribute("cart");
        int index = cart.getIds().indexOf(id);
        int num = cart.getAmounts().get(index);
        if (num == 1) {
            cart.getIds().remove(index);
            cart.getAmounts().remove(index);
            result = new Result(false);
        } else {
            cart.getAmounts().set(index, num - 1);
            result = new Result(true);
        }
        session.setAttribute("cart", cart);
        out.print(mapper.writeValueAsString(result));
    }
}
