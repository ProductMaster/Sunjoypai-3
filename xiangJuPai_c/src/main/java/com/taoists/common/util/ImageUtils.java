/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.taoists.common.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import com.taoists.common.zxing.LogoConfig;
import com.taoists.common.zxing.ZXingCodeUtils;
import com.taoists.common.zxing.ZXingConfig;

/**
 * @author Jiafa Lv
 * @date Apr 22, 2014 9:13:22 AM
 * @email simon-jiafa@126.com
 * 
 */
public class ImageUtils {
    
	public static final int MEDIUM_WIDTH = 340;
	public static final int MEDIUM_HEIGHT = 250;
	public static final String EXT_MEDIUM_SIZE = "340X250";
	
	public static final int SMALL_SIZE = 160;
	public static final String EXT_SMALL_SIZE = "160X160";
	
	public static final int LANGE_SIZE = 200;
	public static final String EXT_LANGE_SIZE = "200X200";
	/**
	 * 实现图像的等比缩放和缩放后的截取
	 * 
	 * @param inFilePath
	 *            要截取文件的路径
	 * @param outFilePath
	 *            截取后输出的路径
	 * @param width
	 *            要截取宽度
	 * @param hight
	 *            要截取的高度
	 * @throws Exception
	 */

	public static void saveImageAsJpg(String inFilePath, String outFilePath,
			int width, int hight) throws Exception {
		File file = new File(inFilePath);
		InputStream in = new FileInputStream(file);
		File saveFile = new File(outFilePath);

		BufferedImage srcImage = ImageIO.read(in);
		
			if (width > 0 || hight > 0) {
				// 原图的大小
				int sw = srcImage.getWidth();
				int sh = srcImage.getHeight();
				// 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
				if (sw > width && sh > hight) {
					srcImage = resize(srcImage, width, hight);
				} else {
					String fileName = saveFile.getName();
					String formatName = fileName.substring(fileName
							.lastIndexOf('.') + 1);
					ImageIO.write(srcImage, formatName, saveFile);
					return;
				}
			}
			// 缩放后的图像的宽和高
			int w = srcImage.getWidth();
			int h = srcImage.getHeight();
			// 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
			if (w == width) {
				// 计算X轴坐标
				int x = 0;
				int y = h / 2 - hight / 2;
				saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
			}
			// 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
			else if (h == hight) {
				// 计算X轴坐标
				int x = w / 2 - width / 2;
				int y = 0;
				saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
			}
		
		in.close();
	}

	/**
	 * 实现缩放后的截图
	 * 
	 * @param image
	 *            缩放后的图像
	 * @param subImageBounds
	 *            要截取的子图的范围
	 * @param subImageFile
	 *            要保存的文件
	 * @throws IOException
	 */
	private static void saveSubImage(BufferedImage image,
			Rectangle subImageBounds, File subImageFile) throws IOException {
		if (subImageBounds.x < 0 || subImageBounds.y < 0
				|| subImageBounds.width - subImageBounds.x > image.getWidth()
				|| subImageBounds.height - subImageBounds.y > image.getHeight()) {
			System.out.println("Bad   subimage   bounds");
			return;
		}
		BufferedImage subImage = image.getSubimage(subImageBounds.x,
				subImageBounds.y, subImageBounds.width, subImageBounds.height);
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		ImageIO.write(subImage, formatName, subImageFile);
	}

	/**
	 * 实现图像的等比缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private static BufferedImage resize(BufferedImage source, int targetW,
			int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
	
	/**
	 * 保存网络图片至本地
	 * 
	 * @param URLName照片地址
	 * @param target本地地址	 
	 * @return
	 */
	public static void getUrlImg2File(String URLName, String target, String defaultImagePath) throws Exception {   
		
		if(!StringUtils.isNoEmpty(URLName.trim())){
			System.out.println("logo path is null, create default header logo!  ===>"+target);
			copyFile(target, defaultImagePath);
			return;
		}
		
		int HttpResult = 0; //服务器返回的状态   

		URL url = new URL(URLName); //创建URL   
		URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码urlconn.connect();   
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;   
		HttpResult = httpconn.getResponseCode();   
		System.out.println(HttpResult);   
		if (HttpResult != HttpURLConnection.HTTP_OK){ // 不等于HTTP_OK说明连接不成功
			System.out.print("Read image url connection fail");  
			copyFile(defaultImagePath, target);
		} else {   
			int filesize = urlconn.getContentLength(); // 取数据长度System.out.println(filesize);   
			BufferedInputStream bis=new BufferedInputStream(urlconn.getInputStream());   
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(target));   
			byte[] buffer = new byte[1024]; //创建存放输入流的缓冲   
			int num = -1; //读入的字节数   
			while (true) {   
				num = bis.read(buffer); // 读入到缓冲区   
				if (num ==-1){   
					bos.flush();   
					break; //已经读完   
				}
				bos.flush();   
				bos.write(buffer,0,num);   
			}  
			bos.close();   
			bis.close();   
		}   
	}
	
	// 生成二维码
	public static void generateQRCode(String filePath, String logoPath, String url){
	    
	    try {
	        System.out.println("generateQRCode start ...");
            // 生成二维码
            File file = new File(filePath);                 // 保存地址
            ZXingCodeUtils zp = new ZXingCodeUtils();       // 实例化二维码工具
            ZXingConfig zxingconfig = new ZXingConfig();    // 实例化二维码配置参数
            zxingconfig.setHints(zp.getDecodeHintType());   // 设置二维码的格式参数
            zxingconfig.setContent(url);                    // 设置二维码生成内容
            zxingconfig.setLogoPath(logoPath);              // 设置Logo图片
            zxingconfig.setLogoConfig(new LogoConfig());    // Logo图片参数设置   
            zxingconfig.setLogoFlg(true);                   // 设置生成Logo图片
            BufferedImage bim = zp.getQR_CODEBufferedImage(zxingconfig);// 生成二维码
            ImageIO.write(bim, "png", file);                // 图片写出
            Thread.sleep(500);                              // 缓冲

            zp.parseQR_CODEImage(bim);  // 解析调用
        } catch (Exception e) {
            System.out.println("generateQRCode fail ...");
            e.printStackTrace();
        }
	}
	
	public static void copyFile(String fileOutPut, String fileIn) throws Exception{
		FileInputStream fileInputStream=new FileInputStream(fileIn);  
        FileOutputStream fileOutputStream=new FileOutputStream(fileOutPut);  
        byte[] by=new byte[1024];  
        int len;  
        while((len=fileInputStream.read(by))!=-1)  
        {  
            fileOutputStream.write(by, 0, len);  
        }  
        fileInputStream.close();  
        fileOutputStream.close();  
	}
}
