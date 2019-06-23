package vnj.sql2.dao;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;
import vnj.sql2.Sql;
import vnj.sql2.entity.User;
import vnj.sql2.entity.Warp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlExecutor {

    private static SqlService sql;
    private static Sql sqlL;
    public SqlExecutor(Sql sqlL){
        SqlExecutor.sqlL = sqlL;
    }
    private static final String JDBC_URL = "jdbc:h2:~/test/database;mode=mysql";
    private static javax.sql.DataSource getDataSource() throws SQLException {
        if (sql == null) {
            sql = Sponge.getServiceManager().provide(SqlService.class).get();
        }
        return sql.getDataSource(SqlExecutor.JDBC_URL);
    }

    public static boolean executeSQLQuery(String sqlQuery) {
        try (Connection connection = getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                final int updatedRows = statement.executeUpdate(sqlQuery);
                connection.commit();
                sqlL.getLogger().info("Sql commit");
                if (updatedRows == 1)
                    return true;
            } catch(SQLException e){
                connection.rollback();
                sqlL.getLogger().error("SQL request execution error. Cause: " + e.getMessage());
            }
        } catch(SQLException e){
            sqlL.getLogger().error("SQL request execution error. Cause: " + e.getMessage());
        }
        return false;
    }

    static User findUser(String sqlQuery) {
        try (Connection connection = getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                User user = null;
                while (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("user_name"));
                    user.setWarp(resultSet.getInt("warp_left"));
                    user.setRtp(resultSet.getInt("rtp_left"));
                }
                connection.commit();
                return user;
            } catch (SQLException e){
                connection.rollback();
                sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
            }
        } catch (SQLException e) {
            sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
        }
        return null;
    }

    static Warp findWarp(String sqlQuery) {
        try (Connection connection = getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                Warp warp = new Warp();
                while (resultSet.next()) {
                    warp.setUser(new UserRepository().finById(resultSet.getInt("id_user")));
                    warp.setWarp_name(resultSet.getString("warp_name"));
                    warp.setWarp_count(resultSet.getInt("warp_count"));
                }
                connection.commit();
                return warp;
            } catch (SQLException e){
                connection.rollback();
                sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
            }
        } catch (SQLException e) {
            sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
        }
        return null;
    }

    static List<Warp> findAllWarp(String sqlQuery) {
        try (Connection connection = getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                List<Warp> warpList = new ArrayList<>();
                while (resultSet.next()) {
                    Warp warp = new Warp();
                    warp.setUser(new UserRepository().finById(resultSet.getInt("id_user")));
                    warp.setWarp_name(resultSet.getString("warp_name"));
                    warp.setWarp_count(resultSet.getInt("warp_count"));
                    warpList.add(warp);
                }
                connection.commit();
                return warpList;
            } catch (SQLException e){
                connection.rollback();
                sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
            }
        } catch (SQLException e) {
            sqlL.getLogger().warn("SQL request execution error. Cause: " + e.getMessage());
        }
        return null;
    }


}
