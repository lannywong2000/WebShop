package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;;
import common.Result;
import pojo.Cart;
import pojo.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/servlet/CartServlet")
public class CartServlet extends HttpServlet {

    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        if (session.getAttribute("member") == null) {
            result = new Result(false, "not login");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.findById(id);
            if (product == null) {
                result = new Result(false, "no such item");
            } else {
                Cart cart = (Cart) session.getAttribute("cart");
                List<Integer> ids;
                List<Integer> amounts;
                if (cart == null) {
                    if (product.getInventory() == 0) {
                        result = new Result(false, "no inventory");
                    } else {
                        cart = new Cart();
                        ids = new ArrayList<>();
                        ids.add(id);
                        amounts = new ArrayList<>();
                        amounts.add(1);
                        cart.setAmounts(amounts);
                        cart.setIds(ids);
                        result = new Result(true);
                    }
                } else {
                    int index = cart.getIds().indexOf(id);
                    if (index == -1) {
                        if (product.getInventory() == 0) {
                            result = new Result(false, "no inventory");
                        } else {
                            cart.getIds().add(id);
                            cart.getAmounts().add(1);
                            result = new Result(true);
                        }
                 } else {
                        amounts = cart.getAmounts();
                        if (amounts.get(index) >= product.getInventory()) {
                            result = new Result(false, "no inventory");
                        } else {
                            amounts.set(index, amounts.get(index) + 1);
                            result = new Result(true);
                        }
                        }
                }
                session.setAttribute("cart", cart);
            }
        }
        out.print(mapper.writeValueAsString(result));
    }
}
