package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Category;
import pojo.Member;
import pojo.OrderList;
import pojo.OrderProfile;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Member> findAllMember() {
        String sql = "select * from member";
        List<Member> members = null;
        try {
            members = runner.query(sql, new BeanListHandler<>(Member.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public List<OrderProfile> findAllProfile() {
        String sql = "select * from orderprofile";
        List<OrderProfile> orderProfile = null;
        try {
            orderProfile = runner.query(sql, new BeanListHandler<>(OrderProfile.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderProfile;
    }

    public List<OrderList> findAllList() {
        String sql = "select * from orderlist";
        List<OrderList> orderList = null;
        try {
            orderList = runner.query(sql, new BeanListHandler<>(OrderList.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

}
