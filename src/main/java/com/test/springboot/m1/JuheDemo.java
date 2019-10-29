package com.test.springboot.m1;


import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class JuheDemo {


    public static void main(String[] args) throws IOException {
        List<String> strings = FileUtils.readLines(new File("code.csv"), "utf-8");
        Connection connection = getConnection();
        strings.forEach(t->{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into ec_coupon_unique_code (id,coupon_id,unique_code,lock_status) values (?,?,?,?)");
                preparedStatement.setString(1, UUID.randomUUID().toString());
                preparedStatement.setString(2,"lxq");
                preparedStatement.setString(3,"lxq_"+t);
                preparedStatement.setString(4,"0");
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 创建连接对象
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://203.195.136.11:3306/dev_tt_equity_center?useUnicode=true&allowMultiQueries=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8", "root", "cdpwy123");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
