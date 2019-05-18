package com.bjsxt.controller;

import com.bjsxt.pojo.People;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    // 返回为void，表示不跳转
    @RequestMapping("downloadFile")
    public void downloadFile(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // resp.setContentType();和写resp.setHeader("Content-Type",属性值)是等效的
        // 设置响应头（attachment 无论何时，都进行下载）
        resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
        // 浏览器中的默认响应头，inline（能显示则显示，不能显示则下载）
        // resp.setHeader("Content-Disposition", "inline;filename="+fileName);

        // 获取某个资源的完整路径
        String path = req.getServletContext().getRealPath("files");
        File file = new File(path, fileName);
        // 将文件转换为二进制流
        byte[] bytes = FileUtils.readFileToByteArray(file);

        // 字符流
        // PrintWriter out = resp.getWriter();
        // 字节流
        ServletOutputStream os = resp.getOutputStream();
        os.write(bytes);
        os.flush();
        os.close();
    }
}
