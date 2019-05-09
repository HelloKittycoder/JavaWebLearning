package com.bjsxt.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shucheng on 2019-5-8 下午 21:53
 * 生成验证码
 */
@WebServlet("/validcode")
public class ValidCodeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一张图片
        // 单位：像素
        BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);

        // 透明的玻璃
        // 向画板上画内容之前必须先设置画笔
        Graphics2D gra = image.createGraphics();
        gra.setColor(Color.WHITE);
        // 从哪个坐标开始填充，后两个参数，矩形区域
        gra.fillRect(0, 0, 200, 100);

        List<Integer> randList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            // random.nextInt(n) 生成[0,n)的随机数
            // 下面是生成0-9的随机数
            randList.add(random.nextInt(10));
        }
        gra.setColor(Color.BLACK);
        // 设置字体（同时应用斜体和加粗两种字体样式，字体大小为40）
        // 设置颜色随机
        Color[] colors = new Color[]{Color.RED, Color.YELLOW, Color.BLUE,
            Color.GREEN, Color.PINK, Color.GRAY};
        gra.setFont(new Font("宋体", Font.ITALIC|Font.BOLD, 40));
        for (int i = 0; i < randList.size(); i++) {
            gra.setColor(colors[random.nextInt(colors.length)]);
            // 产生[-10,10]的上下随机波动
            gra.drawString(randList.get(i) + "", i*40, 70 + (random.nextInt(21) - 10));
        }

        // 画横线
        // gra.setColor(colors[random.nextInt(colors.length)]);
        // gra.drawLine(0, 50, 200, 50);
        // 画两条横线（纵坐标随机）
        for (int i = 0; i < 2; i++) {
            gra.setColor(colors[random.nextInt(colors.length)]);
            gra.drawLine(0, random.nextInt(101), 200, random.nextInt(101));
        }

        ServletOutputStream outputStream = resp.getOutputStream();
        // 工具类
        ImageIO.write(image, "jpg", outputStream);

        // 把验证码放到session中
        HttpSession session = req.getSession();
        session.setAttribute("code", StringUtils.join(randList, ""));
    }
}
