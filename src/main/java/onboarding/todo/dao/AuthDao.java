package onboarding.todo.dao;

import onboarding.todo.entity.Auth;

import java.sql.*;
import java.util.Map;


//https://www.youtube.com/watch?v=E-20r4UdIdo&list=PLAdQRRy4vtQTJawYfraUTUf6rCfWFYqKj&index=8
//전적으로 이 영상의 도움을 받음.

public class UserDao {

    public Auth select(String email) throws SQLException { //영상에서는 id 나는 Email
        Map<String, String> env = System.getenv();
        Connection connection =  DriverManager.getConnection(
                //jdbc:mysql://localhost:3306/todo-db
                //config
                env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
        );
//        Statement stmt = connection.createStatement();
//        stmt.executeQuery("SELECT * FROM USERS WHERE EMAIL = ?");
        //물음표가 들어가야해서 PreparedStatment를 사용
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Auth auth = new Auth(rs.getString("email"), rs.getString("password"));
        rs.close();
        pstmt.close();
        connection.close();

        return auth;
    }

    public static void main(String[] args) {
        U
    }
}
