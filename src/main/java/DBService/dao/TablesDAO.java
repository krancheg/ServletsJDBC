package DBService.dao;

import DBService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class TablesDAO {

    private Executor executor;

    public TablesDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTables() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, " +
                "login varchar(256), pass varchar(256), email varchar(256), primary key (id))");
        executor.execUpdate("create table if not exists sessions (id bigint auto_increment, user_id bigint, primary key (id))");
    }

    public void dropTables() throws SQLException {
        executor.execUpdate("drop table if exists users");
        executor.execUpdate("drop table if exists sessions");
    }
}
