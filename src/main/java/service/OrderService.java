package service;

import dao.OrderDao;
import pojo.Member;
import pojo.OrderList;
import pojo.OrderProfile;

import java.util.List;

public class OrderService {

    OrderDao orderDao = new OrderDao();

    public List<Member> findAllMember() {
        return  orderDao.findAllMember();
    }

    public List<OrderProfile> findAllProfile() {
        return orderDao.findAllProfile();
    }

    public List<OrderList> findAllList() {
        return orderDao.findAllList();
    }

}
