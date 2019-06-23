package vnj.sql2.dao;

import vnj.sql2.entity.Warp;
import java.util.List;

public interface WarpRepositoryImpl {

    Warp findWarpByWarpName(String warp_name);

    List<Warp> findWarpByUserId(int id_user);

    boolean save(Warp warp);

    boolean updateByWarpName(Warp warp, String warp_name);

    boolean removeByWarpName(String warp_name);

}
