package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Cart;
import pojo.Member;
import service.BuyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/PayServlet")
public class PayServlet extends HttpServlet {

    BuyService buyService = new BuyService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Member member = (Member) session.getAttribute("member");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            boolean suc = buyService.buy(member, cart);
            if (suc) {
                result = new Result(true);
                session.removeAttribute("cart");
            } else {
                result = new Result(false);
            }
        } else {
            result = new Result(false);
        }
        out.print(mapper.writeValueAsString(result));
    }
}
