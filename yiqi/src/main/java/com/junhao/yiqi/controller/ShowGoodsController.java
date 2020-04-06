package com.junhao.yiqi.controller;

import com.junhao.yiqi.dao.productMapper;
import com.junhao.yiqi.dao.otherImgMapper;
import com.junhao.yiqi.entity.otherImgEntity;
import com.junhao.yiqi.entity.productEntity;
import com.junhao.yiqi.jpa.BrandJpa;
import com.junhao.yiqi.jpa.productJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**\
 *  商品操作
 */
@Controller
public class ShowGoodsController {
    @Autowired
    private BrandJpa brandJpa;
    @Autowired
    private productJpa productJpa;
    @Autowired
    private productMapper productMapper;
    @Autowired
    private otherImgMapper otherImgMapper;

    // FileController 是商品信息和图片上传修改的地方   这里搞删除和展示


    //展示商品的页面
    @RequestMapping("/goods/show")
    public String show(Integer id, Model model){
        productEntity productEntity = productMapper.selectByPrimaryKey(id);
        model.addAttribute("product",productEntity);
//        System.out.println("查到goods/show?id= "+id);
        //附图图片路径  list

        List<String> list = new ArrayList<>();
        list.add(0,productEntity.getProductImage());
        List<String> otherImgEntities = otherImgMapper.selectProductFuImg(productEntity.getBrandName(), productEntity.getProductName());
        for (int i = 0; i < otherImgEntities.size(); i++) {
            list.add(otherImgEntities.get(i));
        }
        model.addAttribute("productfu",list);
        return "kankan";
    }

    //修改商品  没有修改图片
    @RequestMapping("/goods/setoutImg")
    public String setgoodsIngoreImg(productEntity p, Model model){// id 还在不在
        productMapper.updateByPrimaryKey(p);
        model.addAttribute("product",productMapper.selectByPrimaryKey(p.getId()));
        return "kankan"; //"redirect:/set"   还是放在kankan
    }

    // 获取品牌下的商品名所有
    @RequestMapping("/goods/Productforbrand")
    @ResponseBody
    public List<String> getProductName(String brand){
        return productMapper.allproductname(brand);
    }


    //删除商品
    @RequestMapping("/goods/del")
    public String del(Integer id){
        productJpa.deleteById(id);//userRepository.deleteById(id);
        return "redirect:/set";
    }


}
