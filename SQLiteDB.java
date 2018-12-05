import java.io.*;
import java.sql.*;
import java.util.*;

public class SQLiteDB extends DAO {
    String dbname;
    String connUrl;

    public SQLiteDB(String dbname) {
        this.dbname = dbname;
        this.connUrl = "jdbc:sqlite:" + dbname;
        try (Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement()) {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS phonebook (\n"
                            + "	name text PRIMARY KEY,\n"
                            + "	number text NOT NULL\n"
                            + ");";
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }

	@Override
	public void put(String name, String number) {
        try (Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement()) {
            if (conn != null) {
                String sql = String.format("INSERT INTO phonebook VALUES('%s', '%s');", name, number);
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }

	@Override
	public String get(String name) {
        try (Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement()) {
            if (conn != null) {
                String sql = String.format("SELECT number from phonebook where name = '%s';", name);
                ResultSet result = stmt.executeQuery(sql);
                return result.getString("number");
            }
        } catch (SQLException e) { }
        return null;
    }

	@Override
	public void edit(String name, String number) {
        try (Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement()) {
            if (conn != null) {
                String sql = String.format("UPDATE phonebook SET number = '%s' where name = '%s';", number, name);
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }

	@Override
	public void delete(String name) {
        try (Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement()) {
            if (conn != null) {
                String sql = String.format("DELETE from phonebook where name = '%s';", name);
                stmt.execute(sql);
            }
        } catch (SQLException e) { }
    }
}