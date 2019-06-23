package vnj.sql2.services;

import vnj.sql2.Vnjlog;
import vnj.sql2.dao.UserRepository;
import vnj.sql2.entity.User;

public class UserService implements UserServiceImpl {

    private UserRepository userRepository = new UserRepository();

    @Override
    public boolean addNewUser(User newUser) {
        boolean addingResult = userRepository.save(newUser);
        if (addingResult) {
            Vnjlog.sql.getLogger().info("New user was added. User: " + newUser.toString());
        } else {
            Vnjlog.sql.getLogger().warn("User was not added. User: " + newUser.toString());
        }
        return addingResult;
    }

    @Override
    public boolean removeUserByName(String name) {
        boolean deletingResult = userRepository.removeByName(name);
        if (deletingResult) {
            Vnjlog.sql.getLogger().info("User was deleted by name: " + name);
        } else {
            Vnjlog.sql.getLogger().warn("User was not deleted by name: "  + name);
        }
        return deletingResult;
    }

    @Override
    public boolean isExist(String name) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User was not found by name: ".concat(name));
            return false;
        }
        Vnjlog.sql.getLogger().info("User found. Name: " + user.getName());
        return true;
    }

    @Override
    public boolean setWarpByName(String name, int warp) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User not found by name. Name: " + name);
            return false;
        }
        user.setWarp(warp);
        Vnjlog.sql.getLogger().info("Warp for user with name: '" + name + "' was changed on " + warp);
        return userRepository.updateUserByName(user, name);
    }

    @Override
    public boolean setRtpByName(String name, int rtp) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User not found by name. Name: " + name);
            return false;
        }
        user.setRtp(rtp);
        Vnjlog.sql.getLogger().info("Rtp for user with name: '" + name + "' was changed on " + rtp);
        return userRepository.updateUserByName(user, name);
    }

    @Override
    public boolean changeWarpByName(String name, boolean increment) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User not found by name. Name: " + name);
            return false;
        }
        if (increment)
            user.setWarp(user.getWarp()+1);
        else
            user.setWarp(user.getWarp()-1);
        Vnjlog.sql.getLogger().info("Warp for user with name: '" + name + "' Reduced by 1");
        return userRepository.updateUserByName(user, name);
    }

    @Override
    public boolean changeRtpByName(String name, boolean increment) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User not found by name. Name: " + name);
            return false;
        }
        if (increment)
            user.setRtp(user.getRtp()+1);
        else
            user.setRtp(user.getRtp()-1);
        Vnjlog.sql.getLogger().info("Rtp for user with name: '" + name + "' Reduced by 1");
        return userRepository.updateUserByName(user, name);
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null){
            Vnjlog.sql.getLogger().warn("User not found by name. Name: " + name);
            return null;
        }
        Vnjlog.sql.getLogger().info("User found. Name: " + user.getName());
        return user;
    }

}
