package web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Result;
import pojo.Member;
import pojo.OrderList;
import pojo.OrderProfile;
import service.OrderService;

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

@WebServlet("/servlet/OrderServlet")
public class OrderServlet extends HttpServlet {

    OrderService orderService = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Result result;
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<Member> members = orderService.findAllMember();
        List<OrderProfile> profiles = orderService.findAllProfile();
        List<OrderList> lists = orderService.findAllList();
        if (members != null && profiles != null && lists != null) {
            List<Member> newMems = new ArrayList<>();
            List<OrderList> newLists = new ArrayList<>();
            for (int i = 0; i < profiles.size(); i++) {
                for (int j = 0; j < members.size(); j++) {
                    if (members.get(j).getId() == profiles.get(i).getCid()) {
                        newMems.add(members.get(j));
                        break;
                    }
                }
                for (int j = 0; j < lists.size(); j++) {
                    if (lists.get(j).getOid() == profiles.get(i).getOid()) {
                        newLists.add(lists.get(j));
                    }
                }
            }
            result = new Result(true, profiles, newMems, newLists, "");
        } else {
            result = new Result(false);
        }
        out.print(mapper.writeValueAsString(result));
    }
}
