package com.junhao.yiqi.controller;

import com.junhao.yiqi.entity.BrandEntity;
import com.junhao.yiqi.jpa.BrandJpa;
import com.junhao.yiqi.jpa.productJpa;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class otherController {
    @Autowired
    private BrandJpa brandJpa;
    @Autowired
    private productJpa productJpa;

    //展示品牌表  删除品牌表
    @RequestMapping("/set")
    public String ddd(Model model){
        model.addAttribute("list",brandJpa.findAll());
        model.addAttribute("goods",productJpa.findAll());
        return "brand/DealBrand";
    }

    @RequestMapping("/brand/delBrand")
    public String del(Integer id){
        //删除产品表下所有这个品牌的  删除了品牌，那个品牌下面的商品都删掉
        String s = brandJpa.nativeQuerybrand(id);//查找品牌名
        //product表
        if(!s.isEmpty()) {
            productJpa.delByBrand(s);
        }
        brandJpa.deleteById(id);
        return "redirect:/set";
    }

    @RequestMapping("/brand/addBrand")
    public String addit(BrandEntity brandEntity){
        brandEntity.setCreatetime(new Date());
        brandJpa.save(brandEntity);
        return "redirect:/set";
    }

    //修改
    @RequestMapping("/manageBrand")
    //@ResponseBody
    public String ssset(Integer id,Model model){

        //return brandJpa.nativeQuerybrand(id);
        model.addAttribute("idit",id);
        model.addAttribute("co",brandJpa.nativeQuerybrand(id));
        return "brand/setbrandname";
    }
    @RequestMapping("/brand/setBrand")
    public String sssset(BrandEntity brandEntity){
        brandEntity.setCreatetime(new Date());
        brandJpa.save(brandEntity);
        //brandJpa.updateStateByMoney(brandEntity.getId(),brandEntity.getBrand());
        return "redirect:/set";
    }

    //查找所有品牌名给下拉选择框
    @RequestMapping("/AllBrandName")
    @ResponseBody
    public List ssssset(){
        return brandJpa.nativeQueryfindAllbrandName();
    }




    //测试 上传路径
    @RequestMapping("/test")
    @ResponseBody
    public String getImgDirFile(HttpServletRequest request) {

        // 构建上传文件的存放 "文件夹" 路径
//        String fileDirPath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\classes\\static\\upload\\");
        String fileDirPath = request.getSession().getServletContext().getRealPath("WEB-INF/classes/static/upload");
        return fileDirPath;
    }

}
