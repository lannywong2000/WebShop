package service;

import dao.DelDao;

public class DelService {

    DelDao delDao = new DelDao();

    public boolean del(int id){
        return delDao.del(id);
    }

}
