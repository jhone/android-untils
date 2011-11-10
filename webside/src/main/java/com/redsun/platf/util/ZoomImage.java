package com.redsun.platf.util;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) </p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author Dick Pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>/3/14   : Created </p>
 *
 */
/************************************************
 * <p>java对图片的操作(只能使用jpg)</p>
 * 对图片的签章<br>
 * 对图片的缩图<br>
 * <p>Title:java对图片的操作(只能使用jpg)</p>
 * <p>CreateData: -12-2</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) </p>
 * @author 王凯
 * @version 1.0
 ***********************************************/

import java.io.*;

import com.sun.image.codec.jpeg.*;//sun公司仅提供了jpg图片文件的编码api

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

public class ZoomImage {

    /**
     * @author wangkai
     */

    @SuppressWarnings("restriction")
    public ZoomImage() {
	try {
	    // 生成以后新的图片地址
	    File fo = new File("d:\\result.jpg");
	    // 读取的图片文件
	    String imagePath = "d:\\test.jpg";
	    // 盖章的图片文件
	    String toimagepth = "d:\\test_48_60.jpg";
	    // 得到图片的文件流
	    InputStream imageIn;
	    imageIn = new FileInputStream(new File(imagePath));
	    // 得到输入的编码器，将文件流进行jpg格式编码
	    JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
	    // 得到编码后的图片对象
	    BufferedImage image = decoder.decodeAsBufferedImage();
	    
	    Graphics g = image.getGraphics();
	    try {
		InputStream imageIn2 = null;
		imageIn2 = new FileInputStream(new File(toimagepth));
		// 得到输入的编码器，将文件流进行jpg格式编码
		JPEGImageDecoder decoder2 = JPEGCodec.createJPEGDecoder(imageIn2);
		// 得到编码后的图片对象
		BufferedImage image2 = decoder2.decodeAsBufferedImage();

		// 加盖图片章
		ImageObserver obser = null;
		int x = image.getWidth() - image2.getWidth();
		int y = image.getHeight() - image2.getHeight();
		g.drawImage(image2, x, y, obser);
	    } catch (FileNotFoundException e) {
		// 打开文件失败，表示章图片不存在，这时候直接加盖文件章（签名）
		g.setFont(new Font("宋体", Font.PLAIN, 18));
		g.drawString("秋水工作室", image.getWidth()-20*8, image.getHeight() - 20);
		g.drawString("water_wang@xs.zj.cn", image.getWidth()-20*8
			,image.getHeight() - 10);
	    }
	    g.dispose();

	    ImageIO.write(image, "jpeg", fo);
	    System.out.println("ok");
	} catch (FileNotFoundException e) {
	    // 自动生成 catch 块
	    e.printStackTrace();
	} catch (ImageFormatException e) {
	    // 自动生成 catch 块
	    e.printStackTrace();
	} catch (IOException e) {
	    // 自动生成 catch 块
	    e.printStackTrace();
	}

    }

 
    /**
     * 生成指定尺寸的缩略图
     * 
     * @param imageFile 原文件
     * @param height 要生成文件的高度
     * @param width  宽度
     */
    public static void saveFixedBoundIcon(File imageFile, int height, int width)
	    throws Exception {
	double Ratio = 0.0;
	if (imageFile == null || !imageFile.isFile())
	    throw new Exception(imageFile + "找不到指定的文件!");
	String filePath = imageFile.getPath();
	BufferedImage Bi = ImageIO.read(imageFile);

	if ((Bi.getHeight() > height) || (Bi.getWidth() > width)) {
	    if (Bi.getHeight() > Bi.getWidth()) {
		Ratio = (new Integer(height)).doubleValue() / Bi.getHeight();
	    } else {
		Ratio = (new Integer(width)).doubleValue() / Bi.getWidth();
	    }
	    File savefile = new File(filePath + "_" + height + "_" + width
		    + ".jpg");
	    Image Itemp = Bi.getScaledInstance(width, height,
		    Image.SCALE_SMOOTH);
	    AffineTransformOp op = new AffineTransformOp(AffineTransform
		    .getScaleInstance(Ratio, Ratio), null);
	    Itemp = op.filter(Bi, null);

	    try {
		ImageIO.write((BufferedImage) Itemp, "jpeg", savefile);
	    } catch (Exception ex) {

	    }
	}
    }

    public static void main(String[] args) {
   
	ZoomImage ts = new ZoomImage();
    ts=null;
    try {
	ZoomImage.saveFixedBoundIcon(new File(   "d:\\test.jpg"),48,60)  ;
    } catch (Exception e) {
    // 自动生成 catch 块
    e.printStackTrace();
    }
    }
}
