package vnj.sql2.services;

import vnj.sql2.entity.Warp;
import java.util.List;

public interface WarpServiceImpl {

    boolean addWarp(Warp warp);

    boolean removeWarpByName(String warp_name);

    boolean incrementWarpCount(String warp_name);

    Warp getWarpByWarpName(String warp_name);

    List<Warp> getAllWarpByUserId(int user_id);
}
