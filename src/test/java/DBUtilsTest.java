import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import utils.JDBCUtils;

import java.sql.SQLException;

public class DBUtilsTest {

    @Test
    public void test() {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into member values(null, \"15119550705\", \"12345678\", \"lanny\", \"lanny_wong@foxmail.com\")";
        try {
            runner.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
