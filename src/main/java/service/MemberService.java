package service;

import dao.MemberDao;
import pojo.Member;

public class MemberService {

    MemberDao memberDao = new MemberDao();

    public Member login(String phone) {
        Member member = memberDao.findByPhone(phone);
        return member;
    }

    public boolean register(Member member) {
        Member phone = memberDao.findByPhone(member.getPhone());
        if (phone != null) {
            return false;
        } else {
            memberDao.add(member);
            return true;
        }
    }
}
