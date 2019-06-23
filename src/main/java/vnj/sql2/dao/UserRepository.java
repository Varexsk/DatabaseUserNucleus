package vnj.sql2.dao;

import vnj.sql2.entity.User;

public class UserRepository implements UserRepositoryImpl {


    private static final String JDBC_URL = "jdbc:mysql://gs71359:u7luvNP44pZzv@149.202.86.142:3306/gs71359";
    @Override
    public User findByName(String name) {
        final String sqlQuery = String.format("SELECT * FROM users WHERE user_name ='%s'", name);
        return SqlExecutor.findUser(sqlQuery);
    }

    @Override
    public User finById(int id) {
        final String sqlQuery = String.format("SELECT * FROM users WHERE id ='%d'", id);
        return SqlExecutor.findUser(sqlQuery);
    }


    @Override
    public boolean save(User newUser) {
        final String sqlQuery = String.format("INSERT INTO users (user_name, warp_left, rtp_left) VALUES ('%s', '%d', '%d')", newUser.getName(), newUser.getWarp(), newUser.getRtp());
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

    @Override
    public boolean removeByName(String name) {
        final String sqlQuery = String.format("DELETE FROM users WHERE user_name = '%s'", name);
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

    @Override
    public boolean updateUserByName(User newUser, String name) {
        final String sqlQuery = String.format("UPDATE users SET user_name='%s', warp_left='%d', rtp_left='%d' WHERE user_name='%s'", newUser.getName(), newUser.getWarp(), newUser.getRtp(), name);
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

}
