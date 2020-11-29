package service;

import dao.ProductDao;
import pojo.Product;

import java.util.List;

public class ProductService {

    ProductDao productDao = new ProductDao();

    public List<Product> findByCid(int cid) {
        return productDao.findByCid(cid);
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {return productDao.findAll();}

    public List<Product> findByKey(String key) {return productDao.findByKey(key);}
}
