package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tool {
	
	public static Connection getDb() {
		String url = "jdbc:mysql://localhost:3306/travel_db";
        String user = "root";
        String password = "1234";
        Connection conn = null;
        
        try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(url, user, password);
        
        System.out.println("資料庫連線成功！");
        
    } catch (ClassNotFoundException e) {
        System.out.println("找不到 MySQL 驅動程式");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("資料庫連線失敗");
        e.printStackTrace();
    }
    return conn;
	}

}

