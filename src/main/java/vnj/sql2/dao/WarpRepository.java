package vnj.sql2.dao;

import vnj.sql2.entity.Warp;
import java.util.List;

public class WarpRepository implements WarpRepositoryImpl {


    @Override
    public Warp findWarpByWarpName(String warp_name) {
        final String sqlQuery = String.format("SELECT * FROM warp_list WHERE warp_name ='%s'", warp_name);
        return SqlExecutor.findWarp(sqlQuery);
    }

    @Override
    public List<Warp> findWarpByUserId(int id_user) {
        final String sqlQuery = String.format("SELECT * FROM warp_list WHERE id_user ='%d'", id_user);
        return SqlExecutor.findAllWarp(sqlQuery);
    }

    @Override
    public boolean save(Warp warp) {
        final String sqlQuery = String.format("INSERT INTO warp_list (id_user, warp_name, warp_count) VALUES ('%d', '%s', '%d')",
                warp.getUser().getId(), warp.getWarp_name(), warp.getWarp_count());
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

    @Override
    public boolean updateByWarpName(Warp warp, String warp_name) {
        final String sqlQuery = String.format("UPDATE warp_list SET id_user='%d', warp_name='%s', warp_count='%d' WHERE warp_name='%s'",
                warp.getUser().getId(), warp.getWarp_name(), warp.getWarp_count(), warp_name);
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

    @Override
    public boolean removeByWarpName(String warp_name) {
        final String sqlQuery = String.format("DELETE FROM warp_list WHERE warp_name = '%s'", warp_name);
        return SqlExecutor.executeSQLQuery(sqlQuery);
    }

}
