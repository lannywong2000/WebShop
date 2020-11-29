package service;

import dao.CategoryDao;
import pojo.Category;

import java.util.List;

public class CategoryService {

    CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

}
