package service;

import dao.AddDao;

public class AddService {

    AddDao addDao = new AddDao();

    public boolean add(int id) {
        return addDao.add(id);
    }

}
