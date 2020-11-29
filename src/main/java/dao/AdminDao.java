package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Admin;
import utils.JDBCUtils;

import java.sql.SQLException;

public class AdminDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public Admin findByPhone(String phone) {
        String sql = "select * from admin where phone = ?";
        Admin admin = null;
        try {
            admin = runner.query(sql, new BeanHandler<>(Admin.class), phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
