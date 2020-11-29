package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet/ProductServlet")
public class ProductServlet extends HttpServlet {

    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        String scid = request.getParameter("cid");
        if (scid.matches("^[0-9]+(.[0-9]+)?$")) {
            int cid = Integer.parseInt(scid);
            List<Product> productList = productService.findByCid(cid);
            result = new Result(true, productList, "success");
        } else {
            List<Product> productList = productService.findByKey(scid);
            result = new Result(true, productList, "success");
        }
        out.print(mapper.writeValueAsString(result));
    }
}
