package com.bjsxt.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Create by Administrator on 2019/5/12
 */
@Controller
public class DemoController {

    // 返回为void，表示不跳转
    @RequestMapping("uploadFile")
    public String uploadFile(MultipartFile file, String name) throws IOException {
        // 用来测试在做文件上传时，除了接收文件流以外，能否再接收普通参数
        System.out.println("name:" + name);

        // 为避免上传相同文件名的文件出现被覆盖的情形，使用uuid对文件重新命名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")); // 如果需要上传特定类型的文件，可以用suffix再做判断
        String uuid = UUID.randomUUID().toString();

        // 将前端传来的二进制流存放到指定路径下
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:/"+uuid+suffix));
        return "/index.jsp";
    }
}
