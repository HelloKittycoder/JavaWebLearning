package com.kittycoder.datadisplay.action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by shucheng on 2019-6-12 下午 20:56
 * 下载action
 */
public class DownloadAction {

    private String resourceName;
    private InputStream inputStream;
    private String downloadFileName;

    // 下载文件
    public String downloadFile() {
        try {
            String content = "测试文件下载";
            ByteArrayInputStream stream = new ByteArrayInputStream(
                    content.getBytes(StandardCharsets.UTF_8));
            setDownloadFileName(resourceName); // 设置下载文件名
            setInputStream(stream); // 设置下载文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    // 解决下载文件名中文乱码问题
    public String getDownloadFileName() {
        try {
            downloadFileName = new String(downloadFileName.getBytes("gbk"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }
}
