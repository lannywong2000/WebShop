package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Cart;
import pojo.Product;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {

    CartService cartService = new CartService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null) {
            result = new Result(false);
        } else {
            List<Product> productList = cartService.cartToProducts(cart);
            result = new Result(true, cart.getAmounts(), productList, "");
        }
        out.print(mapper.writeValueAsString(result));
    }
}
