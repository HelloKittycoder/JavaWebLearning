package com.kittycoder.action;

import java.io.File;

/**
 * Created by shucheng on 2018/4/15.
 */
public class FileAction {

    private File uploadify;
    private String uploadifyFileName; // Struts2拦截器获得的文件名

    public String uploadFile() {
        /**
         * 该方法里可以写拼接文件保存路径的逻辑
         */
        System.out.println("test");
        System.out.println(uploadify);
        return null;
    }

    public File getUploadify() {
        return uploadify;
    }

    public void setUploadify(File uploadify) {
        this.uploadify = uploadify;
    }

    public String getUploadifyFileName() {
        return uploadifyFileName;
    }

    public void setUploadifyFileName(String uploadifyFileName) {
        this.uploadifyFileName = uploadifyFileName;
    }
}
