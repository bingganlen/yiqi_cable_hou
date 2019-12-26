package com.junhao.yiqi.controller;


import com.alibaba.fastjson.JSON;
import com.junhao.yiqi.entity.ImgEntity;
import com.junhao.yiqi.entity.productEntity;
import com.junhao.yiqi.jpa.ImgJpa;
import com.junhao.yiqi.jpa.productJpa;
import com.junhao.yiqi.util.DateTimeUtil;
import com.junhao.yiqi.util.UploadUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

@Controller
public class FileController {
    @Autowired
    private ImgJpa imgJpa;
    @Autowired
    private productJpa productJpa;

    //上传商品的页面  图片
    @RequestMapping("/upload")
    public String upload(){
        return "product/addProduct";
    }

    /**\
     *   图片（商品上传处理）
     *   textarea 字符串里面如果有换行，保存时会加上 \n
     * @param file
     * @param brand
     * @param productName
     * @param content
     * @param detail
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,String brand,String productName,
            String content,String detail, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        //上传的文件名加上时间戳后缀   DateTimeUtil.dateNumToStr();
        String newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + " " + DateTimeUtil.dateNumToStr() + fileName.substring(fileName.lastIndexOf("."));
        File fileDir = UploadUtils.getImgDirFile();
        System.out.println(fileDir.getAbsolutePath());
        File dest = new File(fileDir.getAbsolutePath() + File.separator + newFileName);;

        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        String height = String.valueOf(bufferedImage.getWidth());
        String width = String.valueOf(bufferedImage.getHeight());//图片高度 像素
        String s = String.valueOf(file.getSize());//System.out.println("文件大小(MultipartFile)： " + file.getSize()   + "    ，另一个方法(File)：" + dest.length());

        try {
            file.transferTo(dest);//绝对路径
            //String absoluteUrl = DigestUtils.md5Hex(new FileInputStream(dest));
            ImgEntity imgEntity = new ImgEntity();
            imgEntity.setAbsolute_url(dest.getAbsolutePath());
            imgEntity.setCreatetime(new Date());
            imgEntity.setMd5(DigestUtils.md5Hex(new FileInputStream(dest)));//MD5校验需要硬盘的绝对路径
            imgEntity.setUrl("/upload/"+newFileName); //src/main/resources/static/uploadIMG20190708085215 20191115232128309.jpg   搞错了  只有图片名称用得上
            imgEntity.setWidth(width);
            imgEntity.setHeight(height);
            imgEntity.setSize(s);
            ImgEntity d = imgJpa.save(imgEntity);
            //System.out.println(" -----------就是那个ID：  " + d.getId());//有效

            //品牌表不需要更新（需写一个页面，增加品牌 删除品牌）  更新商品表没写
            productEntity productEntity = new productEntity();
            productEntity.setBrandName(brand);
            productEntity.setProductName(productName);
            productEntity.setContent(content);
            productEntity.setDetail(detail);
            productEntity.setProductImage("/upload/"+newFileName);//dest.getAbsolutePath() 是硬盘路径
            // src/main/resources/static/upload2015120463354588 20191126233815243.jpg
            productEntity.setProductImageId(d.getId());
            productEntity.setCreatetime(new Date());
            productJpa.save(productEntity);

            return JSON.toJSONString("上传成功！");
        } catch (IOException e) {
            return JSON.toJSONString("上传失败！");
        }

    }

// todo 图片的访问   在 target文件夹下   http://localhost:8081/upload/2015120463354588%2020191126233815243.jpg



    /**\
     *    insert 改为 update
     *   textarea 字符串里面如果有换行，保存时会加上 \n
     * @param file
     * @param brand
     * @param productName
     * @param content
     * @param detail
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadset")
    @ResponseBody
    public String uploadset(@RequestParam("fileset") MultipartFile file,Integer id, String brand,String productName,
                         String content,String detail, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        //上传的文件名加上时间戳后缀   DateTimeUtil.dateNumToStr();
        String newFileName = fileName.substring(0,fileName.lastIndexOf(".")) + " " + DateTimeUtil.dateNumToStr() + fileName.substring(fileName.lastIndexOf("."));
        File fileDir = UploadUtils.getImgDirFile();
        System.out.println(fileDir.getAbsolutePath());
        File dest = new File(fileDir.getAbsolutePath() + File.separator + newFileName);;

        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        String height = String.valueOf(bufferedImage.getWidth());
        String width = String.valueOf(bufferedImage.getHeight());//图片高度 像素
        String s = String.valueOf(file.getSize());//System.out.println("文件大小(MultipartFile)： " + file.getSize()   + "    ，另一个方法(File)：" + dest.length());

        try {
            file.transferTo(dest);//绝对路径
            //String absoluteUrl = DigestUtils.md5Hex(new FileInputStream(dest));
            ImgEntity imgEntity = new ImgEntity();
            imgEntity.setAbsolute_url(dest.getAbsolutePath());
            imgEntity.setCreatetime(new Date());
            imgEntity.setMd5(DigestUtils.md5Hex(new FileInputStream(dest)));//MD5校验需要硬盘的绝对路径
            imgEntity.setUrl("/upload/"+newFileName); //src/main/resources/static/uploadIMG20190708085215 20191115232128309.jpg   搞错了  只有图片名称用得上
            imgEntity.setWidth(width);
            imgEntity.setHeight(height);
            imgEntity.setSize(s);
            ImgEntity d = imgJpa.save(imgEntity);  // 不管了 图片就是新增  没有修改


            //品牌表不需要更新（需写一个页面，增加品牌 删除品牌）  更新商品表没写
            productEntity productEntity = new productEntity();
            productEntity.setId(id);
            productEntity.setBrandName(brand);
            productEntity.setProductName(productName);
            productEntity.setContent(content);
            productEntity.setDetail(detail);
            productEntity.setProductImage("/upload/"+newFileName);//dest.getAbsolutePath()
            productEntity.setProductImageId(d.getId());
            productEntity.setCreatetime(new Date());
            productJpa.save(productEntity);

            return JSON.toJSONString("上传成功！");
        } catch (IOException e) {
            return JSON.toJSONString("上传失败！");
        }

    }




}

