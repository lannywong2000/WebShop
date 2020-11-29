package service;

import dao.BuyDao;
import pojo.Cart;
import pojo.Member;

public class BuyService {

    BuyDao buyDao = new BuyDao();

    public boolean buy(Member member, Cart cart) {
        return buyDao.buy(member, cart);
    }

}
