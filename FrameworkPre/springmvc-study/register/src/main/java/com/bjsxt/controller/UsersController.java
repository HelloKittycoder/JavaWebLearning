package com.bjsxt.controller;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.UsersService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by shucheng on 2019-5-19 下午 20:20
 */
@Controller
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping("register")
    public String register(Users users, MultipartFile file, HttpServletRequest req) {
        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString()
                +originalName.substring(originalName.lastIndexOf("."));

        // 上传的文件存放路径（放到tomcat里webapp下的某个目录）
        String path = req.getServletContext().getRealPath("images") + "/" + fileName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 只能取到webapps文件夹的内容，这里只存文件名（前面路径都是webapps/register/images）
        users.setPhoto(fileName);
        int index = usersService.insRegister(users);
        if (index > 0) {
            // 跳转到下载页面
            return "/main.jsp";
        } else {
            return "redirect:/register.jsp";
        }
    }
}
