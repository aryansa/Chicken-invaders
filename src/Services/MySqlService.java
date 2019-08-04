package Services;

import root.Settings;

import java.sql.*;

public class MySqlService {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySqlService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/"+ Settings.DATABASE[0] +"?"
                        + "user="+Settings.DATABASE[1]+"&password="+Settings.DATABASE[2]);
        statement = connect.createStatement();
    }
    public ResultSet selectAll(String table) throws SQLException {
        preparedStatement = connect
                .prepareStatement("SELECT * from "+table);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public ResultSet select(String table, int id) throws SQLException {
        preparedStatement = connect
                .prepareStatement("SELECT * from "+table+" WHERE id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    public void insert(String table, String[] heads, String[] values) throws SQLException {
        StringBuilder head = new StringBuilder();
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < heads.length - 2; i++) {
            head.append(heads[i]).append(",");
            val.append("?,");
        }
        head.append(heads[heads.length - 1]);
        val.append("?");
        String query = "insert into " + table + " (" + head + ") values (" + val + ")";
        try {
            preparedStatement = connect
                    .prepareStatement(query);
            for (int i = 0; i < heads.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(String table,int id) throws SQLException {
        preparedStatement = connect
                    .prepareStatement("delete from "+table+" where id= ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
    }
}
