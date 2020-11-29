package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Category;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Result result;
        List<Category> categoryList = categoryService.findAll();
        result = new Result(true, categoryList, "success");
        out.print(mapper.writeValueAsString(result));
    }
}
