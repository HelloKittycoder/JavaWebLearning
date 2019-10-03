package com.kittycoder.httpfile;

import com.kittycoder.utils.HttpCallerUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shucheng on 2019-10-1 下午 22:57
 */
public class TestGetFile {

    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>();
        byte[] ret = HttpCallerUtils.getStream("http://192.168.21.1:8765/sources/a.doc", params);
        // byte[] ret = HttpProxy.getStream("http://192.168.1.21:8765/images/006.jpg");

        // 写出文件（单模块里这样写可以，多模块里行不通）
        /*String writePath = System.getProperty("user.dir") + File.separatorChar + "receive" + File.separatorChar + "a.doc";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(ret);
        fos.close();*/

        File receiveDir = new File(TestGetFile.class.getResource("/").getPath() + "receive");
        if (!receiveDir.exists()) {
            receiveDir.mkdirs();
        }
        File f = new File(receiveDir, "a.doc");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(ret);
        fos.close();
    }
}
