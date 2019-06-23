package vnj.sql2.dao;

import vnj.sql2.entity.User;

public interface UserRepositoryImpl {

    User findByName(String name);

    User finById(int id);

    boolean save(User newUser);

    boolean removeByName(String name);

    boolean updateUserByName(User newUser, String name);

}
