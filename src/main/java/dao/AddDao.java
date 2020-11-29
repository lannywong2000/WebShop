package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Product;
import utils.JDBCUtils;

import java.sql.SQLException;

public class AddDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public boolean add(int id) {
        String sql = "select * from product where id = ?";
        Product product = null;
        try {
            product = runner.query(sql, new BeanHandler<>(Product.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (product != null) {
            int inventory = product.getInventory();
            inventory = inventory + 1;
            sql = "update product set inventory = ? where id = ?";
            try {
                runner.update(sql, inventory, id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        }
        return false;
    }

}
