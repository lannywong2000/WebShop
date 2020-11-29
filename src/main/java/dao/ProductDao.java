package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Product;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Product> findByCid(int cid) {
        String sql = "select * from product where cid=?";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class), cid);
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product findById(int id) {
        String sql = "select * from product where id=?";
        try {
            Product product = runner.query(sql, new BeanHandler<>(Product.class), id);
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findAll() {
        String sql = "select * from product";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class));
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findByKey(String key) {
        String sql = "select * from product where name like \"%" + key + "%\"";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class));
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
