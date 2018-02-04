package com.youxinpai.common.util.util;

import com.google.zxing.WriterException;
import com.youxinpai.common.util.utils.QRCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 二维码生成工具类 单元测试
 */
public class QRCodeUtilTest {


    @Test
    public void generateQRCodeToBuffeTest() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateQRCodeToBuffe("http://www.baidu.com");
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void generateLogoQRCodeToBuffeTest() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateQRCodeToBuffe("http://www.baidu.com", new File("d:/logo.png"));
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void generateLogoQRCodeTest() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateQRCodeToBuffe("http://www.baidu.com", ImageIO.read(new File("d:/logo.png")));
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void customQRCodewight() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateQRCodeToBuffe("http://www.baidu.com", 1000, 1000);
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void customLogoQRCodewight() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateQRCodeToBuffe("http://www.baidu.com", 500, 500, new File("d:/logo.png"));
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void customLogoWidth() throws IOException, WriterException {
        BufferedImage bufferedImage = QRCodeUtil.generateCustomQRCodeToBuffe("http://www.baidu.com", 500, 500,
                new File("d:/logo.png"), 200, 200);
        ImageIO.write(bufferedImage, "png", new File("d:/1.png"));
    }

    @Test
    public void customLogoWidth1() throws IOException, WriterException {
        OutputStream outputStream = QRCodeUtil.generateQRCodeToOutputStream(
                "http://www.baidu.com", 500, 500,
                new FileOutputStream("d:/2.png"), new File("d:/logo.png"), 200, 200);
        outputStream.flush();
        outputStream.close();
    }
}
