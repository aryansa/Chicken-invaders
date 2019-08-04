package root;

import Services.MySqlService;
import gpu.GameFrame;

import java.sql.SQLException;

public class Main {
    public static GameFrame frame;
    public static MySqlService mySqlService;
    static {
        try {
            mySqlService = new MySqlService();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {

        frame = new GameFrame();
    }
}
