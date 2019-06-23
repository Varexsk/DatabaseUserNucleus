package vnj.sql2.services;

import vnj.sql2.Vnjlog;
import vnj.sql2.dao.WarpRepository;
import vnj.sql2.entity.Warp;
import java.util.List;

public class WarpService implements WarpServiceImpl {

    private WarpRepository warpRepository = new WarpRepository();

    @Override
    public boolean addWarp(Warp warp) {
        boolean addingResult = warpRepository.save(warp);
        if (addingResult) {
            Vnjlog.sql.getLogger().info("New warp was added. Warp: " + warp.toString());
        } else {
            Vnjlog.sql.getLogger().warn("Warp was not added. User: " + warp.toString());
        }
        return addingResult;
    }

    @Override
    public boolean removeWarpByName(String warp_name) {
        boolean deletingResult = warpRepository.removeByWarpName(warp_name);
        if (deletingResult) {
            Vnjlog.sql.getLogger().info("Warp was deleted by name: " + warp_name);
        } else {
            Vnjlog.sql.getLogger().warn("User was not deleted by name: "  + warp_name);
        }
        return deletingResult;
    }

    @Override
    public boolean incrementWarpCount(String warp_name) {
        Warp warp = warpRepository.findWarpByWarpName(warp_name);
        if (warp == null){
            Vnjlog.sql.getLogger().warn("Warp not found by name. Name: " + warp_name);
            return false;
        }
        warp.setWarp_count(warp.getWarp_count()+1);
        Vnjlog.sql.getLogger().info("Warp_count for warp with : '" + warp_name + "' increased by 1");
        return warpRepository.updateByWarpName(warp, warp_name);
    }

    @Override
    public Warp getWarpByWarpName(String warp_name) {
        Warp warp = warpRepository.findWarpByWarpName(warp_name);
        if (warp == null){
            Vnjlog.sql.getLogger().warn("Warp not found by name. Name: " + warp_name);
            return null;
        }
        Vnjlog.sql.getLogger().info("Warp found. Name: " + warp.getWarp_name());
        return warp;
    }

    @Override
    public List<Warp> getAllWarpByUserId(int user_id) {
        List<Warp> allWarp = warpRepository.findWarpByUserId(user_id);
        if (allWarp == null){
            return null;
        }
        return allWarp;
    }
}
