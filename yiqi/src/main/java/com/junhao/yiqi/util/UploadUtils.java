package com.junhao.yiqi.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UploadUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IMG_PATH_PREFIX = "static/upload";

    public static File getImgDirFile(HttpServletRequest request){

        // 构建上传文件的存放 "文件夹" 路径
        //String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);
        //String fileDirPath = request.getSession().getServletContext().getRealPath("\\static\\upload\\");//springmvc这个没错   springboot打包war后目录不一样
//        String fileDirPath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes\\static\\upload\\");//用了很久的
        String fileDirPath = request.getSession().getServletContext().getRealPath("WEB-INF/classes/static/upload");
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
