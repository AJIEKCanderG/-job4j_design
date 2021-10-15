package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * @author Ajiekcander
 */

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driver_class"));
            DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No connection, error, try again");
        }
    }

    public void executeSql(String sql) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = "CREATE table if not exists " + tableName;
        executeSql(sql);
    }

    public void dropTable(String tableName) {
        String sql = "DROP table " + tableName;
        executeSql(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "ALTER table + " + tableName + " add column " + columnName + " " + type;
        executeSql(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "ALTER table + " + tableName + " drop column " + columnName;
        executeSql(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "ALTER table + " + tableName + " rename column " + columnName + " to " + newColumnName;
        executeSql(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
