package service;

import dao.ProductDao;
import pojo.Cart;
import pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    ProductDao productDao = new ProductDao();

    public List<Product> cartToProducts(Cart cart) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < cart.getIds().size(); i++) {
            productList.add(productDao.findById(cart.getIds().get(i)));
        }
        return productList;
    }

}
