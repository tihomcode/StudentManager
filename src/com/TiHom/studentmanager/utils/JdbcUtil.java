package com.TiHom.studentmanager.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 2018/4/8 12:09
 * 手写的Jdbc工具类
 * @author TiHom
 */
public class JdbcUtil {

    /**
     * 获取连接
     * @return 连接
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        //获取配置文件位置，获取db.properties对应的输入流
        InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
        //加载输入流
        properties.load(in);
        //读取信息进行连接
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String jdbcUrl = properties.getProperty("jdbcUrl");
        String driver = properties.getProperty("driver");
        //加载数据库驱动程序（对应的Driver 实现类中有注册驱动的静态代码块）
        Class.forName(driver);
        //通过驱动类的getConnection方法获取数据库连接
        return DriverManager.getConnection(jdbcUrl,user,password);
    }

    /**
     * 释放资源
     * @param rs
     * @param statement
     * @param connection
     * @throws SQLException
     */
    public static void releaseConnection(ResultSet rs,Statement statement,Connection connection){
        //关闭结果集
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        //关闭
        if(statement!=null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //关闭连接
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     * @param statement
     * @param connection
     */
    public static void realeaseConnection(Statement statement,Connection connection){
        //关闭
        if(statement!=null){
            try{
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //关闭连接
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = JdbcUtil.getConnection();
            if(connection==null){
                System.out.println("没连接上数据库！");
            }else {
                System.out.println("连接成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
