package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Cart;
import pojo.Member;
import pojo.Product;
import utils.DateUtils;
import utils.HashUtils;
import utils.JDBCUtils;

import java.sql.SQLException;

public class BuyDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public boolean buy(Member member, Cart cart) {
        int id = new HashUtils(member, cart).hash();
        String sql = "insert into orderprofile values(null, ?, ?, ?)";
        try {
            runner.update(sql, id, member.getId(), DateUtils.data2string(member.getRtime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < cart.getIds().size(); i++) {
            int pid = cart.getIds().get(i);
            sql = "select * from product where id=?";
            Product product = null;
            try {
                product = runner.query(sql, new BeanHandler<>(Product.class), pid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int inventory = product.getInventory();
            int sales = product.getSales();
            double price = product.getSale_price();
            sql = "insert into orderlist values(?, ?, ?, ?)";
            try {
                runner.update(sql, id , pid, cart.getAmounts().get(i), price);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int newInventory = inventory - cart.getAmounts().get(i);
            int newSales = sales + cart.getAmounts().get(i);
            sql = "update product set inventory = ? , sales = ? where id = ?";
            try {
                runner.update(sql, newInventory, newSales, pid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
