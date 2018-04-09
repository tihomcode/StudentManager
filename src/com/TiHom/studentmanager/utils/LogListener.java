package com.TiHom.studentmanager.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 2018/4/7 14:55
 * 手写的日志监听器
 * @author TiHom
 */
public class LogListener {
    private LogUtil logger = new LogUtil();

    public void LogListen() throws ParseException {
        long daySpan = 24*60*60*1000;
        //规定每天执行时间
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '00:00:00'");
        //首次允许时间
        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
        if(System.currentTimeMillis()>startTime.getTime()){
            startTime = new Date(startTime.getTime()+daySpan);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                int firstDate = 0;
                int lastDate = 0;
                @Override
                public void run() {
                    logger.writeToLog("管理员：","---【监听器监听到开始删除日志文件】---");
                    String logPath = "/log";
                    firstDate = logger.getForwardDate(-2);
                    lastDate = logger.getForwardDate(-1);
                    //首先匹配文件夹
                    if(new File(logPath).isDirectory()){
                        //获取文件夹中文件集合
                        File[] logs = new File(logPath).listFiles();
                        //遍历文件
                        for(File file:logs){
                            //获取文件名称
                            String filename = file.getName();
                            //将文件名转为数字
                            int logInt = Integer.parseInt(filename.substring(0,filename.lastIndexOf(".")));
                            //获取那个时间期间内的文件,通过比较大小来判断
                            if(logInt>=firstDate&&logInt<=lastDate){
                                //删除文件
                                file.delete();
                            }
                        }
                    }
                    logger.writeToLog("管理员：","---【监听器监听到结束删除日志文件】---");
                }
            };
            timer.scheduleAtFixedRate(timerTask,startTime,daySpan);
        }

    }
}
