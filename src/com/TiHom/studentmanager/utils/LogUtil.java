package com.TiHom.studentmanager.utils;


import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 2018/3/30 13:33
 * 手写的日志工具类
 * @author TiHom
 */
public class LogUtil {
    /**
     * MD5密码加盐加密
     */
    public static String MD5Util(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            //把每一个byte做一个与运算0xff
            for(byte b:result){
                //与运算，加盐
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if(str.length()==1){
                    buffer.append("0");
                }
                buffer.append(str);
            }
            //标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 日志写入
     */
    public static void writeToLog(String userName,String message){
        try {
            String dateFormat = new SimpleDateFormat("yyyy/MM/dd EEE HH:mm:ss").format(new Date());
            String daytime = new SimpleDateFormat("yyyyMMdd").format(new Date());
            Date date = new Date();
            String content = dateFormat +"   " + userName +":"+ message + "\n";
            PrintWriter outputStream = null;
            outputStream = new PrintWriter(new FileOutputStream("log/"+daytime+".log",true));
            outputStream.println(content);
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前月往前推一个月的日期
     */
    public static int getForwardDate(int what) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MONTH, what);//从现在算，之前month个月
        Date dateFrom = cl.getTime();
        return Integer.parseInt(sdf.format(dateFrom));
    }


}
