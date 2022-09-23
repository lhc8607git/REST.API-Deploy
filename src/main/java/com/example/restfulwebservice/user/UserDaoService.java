package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service    // @Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;  // 초기 데이터

    static {
        users.add(new User(1, "Kenneth", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "Alice", new Date(), "pass2", "801010-2222222"));
        users.add(new User(3, "Elena", new Date(), "pass2", "901010-1111111"));
    }

    // 사용자 전체
    public List<User> findAll() {
        return users;
    }

    // 사용자 추가
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    // 사용자 개별 데이터
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        // 사용자가 없는 건 null 처리리
       return null;
    }


    // 사용자 삭제
    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }



//    public User update(int id, User user) {
//        for (User storedUser : users) {
//            if (storedUser.getId() == id) {
//                storedUser.setName(user.getName());
//                storedUser.setPassword(user.getPassword());
//
//                return storedUser;
//            }
//        }
//
//        return null;
//    }


}
