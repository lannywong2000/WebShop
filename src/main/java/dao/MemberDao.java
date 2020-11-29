package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Member;
import utils.DateUtils;
import utils.JDBCUtils;

import java.sql.SQLException;

public class MemberDao {

    QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

    public Member findByPhone(String phone) {
        String sql = "select * from member where phone = ?";
        Member member = null;
        try {
            member = runner.query(sql, new BeanHandler<>(Member.class), phone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public void add(Member member) {
        String sql = "insert into member values(null, ?, ?, ?, ?, ?)";
        try {
            runner.update(sql, member.getPhone(), member.getPwd(), member.getName(), member.getEmail(), DateUtils.data2string(member.getRtime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
