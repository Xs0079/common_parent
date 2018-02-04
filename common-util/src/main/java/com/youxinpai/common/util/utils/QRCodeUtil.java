package com.youxinpai.common.util.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.youxinpai.common.util.constant.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具类
 * 需要添加依赖：
 * <p>
 * <dependency>
 * <groupId>com.google.zxing</groupId>
 * <artifactId>javase</artifactId>
 * <version>3.3.2</version>
 * </dependency>
 * <p>
 * Created by Xs on 2018/2/1.
 */
public class QRCodeUtil {
    /**
     * 默认是黑色
     */
    private static final int QRCOLOR = 0xFF000000;

    /**
     * 背景颜色 白色
     */
    private static final int BGWHITE = 0xFFFFFFFF;

    /**
     * 默认二维码宽度
     */
    private static final int DEFAULT_WIDTH = 300;

    /**
     * 默认二维码高度
     */
    private static final int DEFAULT_HEIGHT = 300;

    /**
     * 默认二维码文件格式
     */
    private static final String DEFAULT_FILE_SUFFIX = "png";

    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> hints = new HashMap();


    static {
        // 字符编码
        hints.put(EncodeHintType.CHARACTER_SET, Constant.UTF8_CHARSET);
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码与图片边距
        hints.put(EncodeHintType.MARGIN, 2);
    }


    /**
     * 生成二维码，默认长宽 300 ；
     *
     * @param qrUrl 访问地址
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 生成二维码，默认长宽 300 ；
     *
     * @param qrUrl 访问地址
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static OutputStream generateQRCodeToOutputStream(String qrUrl, OutputStream outputStream) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }

    /**
     * 生成带logo的二维码
     *
     * @param qrUrl     qrUrl
     * @param logoImage 二维码logoBufferImage
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, BufferedImage logoImage) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl), logoImage);
    }

    /**
     * 生成带logo的二维码
     *
     * @param qrUrl    qrUrl
     * @param logoFile 二维码文件
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, File logoFile) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl), logoFile);
    }


    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl  访问地址
     * @param width  二维码宽度
     * @param height 二维码高度
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, int width, int height) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 生成二维码，返回一个流对象
     *
     * @param qrUrl        访问地址
     * @param width        二维码宽度
     * @param height       二维码高度
     * @param outputStream 需要输出的流
     * @param logoFile     logoFile
     * @param logoWidth    logo图片宽度
     * @param logoHeight   logo图片高度
     * @return OutputStream
     * @throws WriterException WriterException
     * @throws IOException     IOException
     */
    public static OutputStream generateQRCodeToOutputStream(String qrUrl, int width, int height, OutputStream outputStream,
                                                            File logoFile, int logoWidth, int logoHeight) throws WriterException, IOException {
        BufferedImage bufferedImage = generateCustomQRCodeToBuffe(qrUrl, width, height, logoFile, logoWidth, logoHeight);
        ImageIO.write(bufferedImage, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }


    /**
     * 生成二维码图片输出到一个流中
     *
     * @param qrUrl  访问地址
     * @param stream 输出流
     * @param width  二维码宽度
     * @param height 二维码高度
     * @return OutputStream
     * @throws WriterException
     * @throws IOException
     */
    public static OutputStream generateQRCodeToOutputStream(String qrUrl, OutputStream stream, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, DEFAULT_FILE_SUFFIX, stream);
        return stream;
    }

    /**
     * 生成二维码并输出到文件中.
     *
     * @param content 二维码内容
     * @param path    文件保存路径
     * @param width   宽
     * @param height  高
     */
    public static void createQRCodeToFile(String content, String path, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToPath(bitMatrix, DEFAULT_FILE_SUFFIX, new File(path).toPath());
    }


    /**
     * 为二维码图片中间添加Logo
     * logo大小为二维码的27%左右
     *
     * @param bufferedImage bufferedImage
     * @param logoFile      logoFile
     */
    private static BufferedImage addLogoToQRCode(BufferedImage bufferedImage, File logoFile) throws IOException {
        return addLogoToQRCode(bufferedImage, ImageIO.read(logoFile));
    }

    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl        访问地址
     * @param width        二维码宽度
     * @param height       二维码高度
     * @param outputStream 需要输出的流
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static OutputStream generateQRCodeToBuffe(String qrUrl, int width, int height, File logoFile, OutputStream outputStream) throws WriterException, IOException {
        BufferedImage bufferedImage = addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), ImageIO.read(logoFile));
        ImageIO.write(bufferedImage, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }

    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl        qrUrl
     * @param logoFile     logoFile
     * @param outputStream 输出流
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static OutputStream generateQRCodeToBuffe(String qrUrl, File logoFile, OutputStream outputStream) throws WriterException, IOException {
        BufferedImage bufferedImage = addLogoToQRCode(generateQRCodeToBuffe(qrUrl, DEFAULT_WIDTH, DEFAULT_HEIGHT), ImageIO.read(logoFile));
        ImageIO.write(bufferedImage, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }

    /**
     * 生成二维码，返回一个OutputStream对象
     *
     * @param qrUrl        url
     * @param logoBuffer   logo BuffedImage对象
     * @param outputStream 输出流
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static OutputStream generateQRCodeToBuffe(String qrUrl, BufferedImage logoBuffer, OutputStream outputStream) throws WriterException, IOException {
        BufferedImage bufferedImage = addLogoToQRCode(generateQRCodeToBuffe(qrUrl, DEFAULT_WIDTH, DEFAULT_HEIGHT), logoBuffer);
        ImageIO.write(bufferedImage, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }

    /**
     * 生成二维码，返回一个OutputStream对象
     *
     * @param qrUrl        url
     * @param logoBuffer   logo BuffedImage对象
     * @param outputStream 输出流
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static OutputStream generateQRCodeToBuffe(String qrUrl, BufferedImage logoBuffer, int width, int height,
                                                     OutputStream outputStream) throws WriterException, IOException {
        BufferedImage bufferedImage = addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), logoBuffer);
        ImageIO.write(bufferedImage, DEFAULT_FILE_SUFFIX, outputStream);
        return outputStream;
    }

    /**
     * 生成二维码，返回一个outputStream
     *
     * @param qrUrl  访问地址
     * @param width  二维码宽度
     * @param height 二维码高度
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, int width, int height, File logoFile) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), ImageIO.read(logoFile));
    }

    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl  访问地址
     * @param width  二维码宽度
     * @param height 二维码高度
     * @return BufferedImage
     * @throws WriterException WriterException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, int width, int height, BufferedImage logoBufferedImage) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), logoBufferedImage);
    }

    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl      访问地址
     * @param width      二维码宽度
     * @param height     二维码高度
     * @param logoFile   logoFile
     * @param logoWidth  logo图片宽度
     * @param logoHeight logo图片高度
     * @return BufferedImage
     * @throws WriterException WriterException
     * @throws IOException     IOException
     */
    public static BufferedImage generateCustomQRCodeToBuffe(String qrUrl, int width, int height,
                                                            File logoFile, int logoWidth, int logoHeight) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), ImageIO.read(logoFile), logoWidth, logoHeight);
    }

    /**
     * 生成二维码，返回一个BufferedImage对象
     *
     * @param qrUrl             访问地址
     * @param width             二维码宽度
     * @param height            二维码高度
     * @param logoBufferedImage logoBufferedImage
     * @param logoWidth         logo图片宽度
     * @param logoHeight        logo图片高度
     * @return BufferedImage
     * @throws WriterException WriterException
     * @throws IOException     IOException
     */
    public static BufferedImage generateQRCodeToBuffe(String qrUrl, int width, int height,
                                                      BufferedImage logoBufferedImage, int logoWidth, int logoHeight) throws WriterException, IOException {
        return addLogoToQRCode(generateQRCodeToBuffe(qrUrl, width, height), logoBufferedImage, logoWidth, logoHeight);
    }

    /**
     * 为二维码图片中间添加Logo
     * logo大小为二维码的27%左右
     *
     * @param qrImageBuffer     qrImageBuffer
     * @param logoBufferedImage logoBuufferedImage
     */
    private static BufferedImage addLogoToQRCode(BufferedImage qrImageBuffer, BufferedImage logoBufferedImage) throws IOException {
        BufferedImage outImgBuffer = new BufferedImage(qrImageBuffer.getWidth(),
                qrImageBuffer.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = outImgBuffer.createGraphics();

        //设置logo的大小 
        int logoWidth = logoBufferedImage.getWidth(null) > qrImageBuffer.getWidth() * 3 / 11 ?
                (qrImageBuffer.getWidth() * 3 / 11) : logoBufferedImage.getWidth(null);
        int logoHeight = logoBufferedImage.getHeight(null) > qrImageBuffer.getHeight() * 3 / 11 ?
                (qrImageBuffer.getHeight() * 3 / 11) : logoBufferedImage.getWidth(null);

        //设置logo坐标
        int x = (qrImageBuffer.getWidth() - logoWidth) / 2;
        int y = (qrImageBuffer.getHeight() - logoHeight) / 2;

        graphics.drawImage(qrImageBuffer, 0, 0, qrImageBuffer.getWidth(), qrImageBuffer.getHeight(), null);
        graphics.drawImage(logoBufferedImage, x, y, logoWidth, logoHeight, null);
        graphics.dispose();
        outImgBuffer.flush();

        return outImgBuffer;
    }

    /**
     * 为二维码图片中间添加Logo
     * logo大小为二维码的27%左右
     *
     * @param qrImageBuffer     qrImageBuffer
     * @param logoBufferedImage logoBuufferedImage
     */
    private static BufferedImage addLogoToQRCode(BufferedImage qrImageBuffer, BufferedImage logoBufferedImage, int logoWidth, int logoHeight) throws IOException {
        BufferedImage outImgBuffer = new BufferedImage(qrImageBuffer.getWidth(),
                qrImageBuffer.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = outImgBuffer.createGraphics();

        //设置logo坐标
        int x = (qrImageBuffer.getWidth() - logoWidth) / 2;
        int y = (qrImageBuffer.getHeight() - logoHeight) / 2;

        graphics.drawImage(qrImageBuffer, 0, 0, qrImageBuffer.getWidth(), qrImageBuffer.getHeight(), null);
        graphics.drawImage(logoBufferedImage, x, y, logoWidth, logoHeight, null);
        graphics.dispose();
        outImgBuffer.flush();

        return outImgBuffer;
    }


    /**
     * 为二维码图片底部添加文字说明
     * <p>
     * 未完善，将来若使用仅供参考的例子，需要考虑字符串过长问题
     *
     * @param bufferedImage bufferedImage
     * @param remark        remark
     * @return bufferedImage
     */
    private static BufferedImage addRemarkToQRCode(BufferedImage bufferedImage, String remark) {
        //新的图片，把二维码下面加上文字
        BufferedImage outImage = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight() + bufferedImage.getHeight() * 1 / 20,
                BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D outg = outImage.createGraphics();
        outg.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        outg.setColor(Color.BLACK);
        outg.setFont(new Font("宋体", Font.BOLD, 15));
        int strWidth = outg.getFontMetrics().stringWidth(remark);
        //画文字 
        int width = bufferedImage.getWidth();
        int x = width / 2 - strWidth / 2 > 0 ? width / 2 - strWidth / 2 : 0;
        int y = bufferedImage.getHeight() + (outImage.getHeight() - bufferedImage.getHeight()) * 1 / 10;
        outg.drawString(remark, x, y);
        outg.dispose();
        outImage.flush();

        return outImage;
    }
}
