package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.Category;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public List<Category> findAll() {
        String sql = "select * from category";
        List<Category> categoryList = null;
        try {
            categoryList = runner.query(sql, new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
}
