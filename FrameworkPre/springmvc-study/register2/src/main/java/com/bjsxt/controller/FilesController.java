package com.bjsxt.controller;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.FilesService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by shucheng on 2019-5-19 下午 23:03
 */
@Controller
public class FilesController {
    @Resource
    private FilesService filesService;

    @RequestMapping("show")
    public String show(Model model) {
        model.addAttribute("list", filesService.show());
        return "/main.jsp";
    }

    @RequestMapping("downloadFile")
    public void downloadFile(int id, String name, HttpServletRequest req,
                               HttpServletResponse resp) throws IOException {
        filesService.updCount(id, (Users) req.getSession().getAttribute("user"), name);
        resp.setHeader("Content-Disposition", "attachment;filename="+name);
        ServletOutputStream os = resp.getOutputStream();
        File file = new File(req.getServletContext().getRealPath("files"), name);
        os.write(FileUtils.readFileToByteArray(file));
        os.flush();
        os.close();
    }
}
