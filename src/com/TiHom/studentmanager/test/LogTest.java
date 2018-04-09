package com.TiHom.studentmanager.test;


import com.TiHom.studentmanager.utils.LogUtil;

import java.io.IOException;

/**
 * 2018/4/5 14:45
 *
 * @author TiHom
 */
public class LogTest {
    public static void main(String[] args) throws IOException {
        LogUtil.writeToLog("我","干了点事情");
    }
}
