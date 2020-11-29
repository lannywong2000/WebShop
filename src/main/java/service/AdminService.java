package service;

import dao.AdminDao;
import pojo.Admin;
import pojo.Member;

public class AdminService {

    AdminDao adminDao = new AdminDao();

    public Admin login(String phone) {
        Admin admin = adminDao.findByPhone(phone);
        return admin;
    }

}
