package vnj.sql2.services;

import vnj.sql2.entity.User;

public interface UserServiceImpl {

    boolean addNewUser(User newUser);

    boolean removeUserByName(String name);

    boolean isExist(String name);

    boolean setWarpByName(String name, int warp);

    boolean setRtpByName(String name, int rtp);

    boolean changeWarpByName(String name, boolean increment);

    boolean changeRtpByName(String name, boolean increment);

    User getUserByName(String name);

}
