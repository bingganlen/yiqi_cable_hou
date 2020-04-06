package com.junhao.yiqi.controller;

import com.junhao.yiqi.dao.ImgMapper;
import com.junhao.yiqi.entity.PageBean;
import com.junhao.yiqi.entity.productEntity;
import com.junhao.yiqi.jpa.productJpa;
import com.junhao.yiqi.service.fenyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class pageHelperController {
    @Autowired
    private productJpa productJpa;
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private fenyeService fenyeService;

// 1 完全放弃
//    @GetMapping(value = "airKind")
//   // @ApiOperation(value = "Air Kind",notes = "气体分类")   注解@ApiOperation是用来构建Api文档的  @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response =“接口返回参数类型”, notes = “接口发布说明”；其他参数可参考源码；
//    public String  airKind(Model model){
//        int pageNum=1;//第几页
//        int pageSize=10;//每页数据条数
//        Page<productEntity> page = PageHelper.startPage(pageNum, pageSize);
//        List<productEntity> listKind  = imgMapper.gettp();//productJpa.findAll();
//        //从数据库中查出所有数据
//
//        System.out.println("总共条数："+page.getTotal());
//        for (productEntity kind : page.getResult()) {
//            System.out.println(kind.getProductName());
//        }
//        model.addAttribute("item",listKind);
//
//        return "knowAir/airKind";
//    }

    //知道大概  不熟悉
//    @PostMapping(value="/findPage")
//    @ResponseBody
//    public Object findPage(@RequestBody PageRequest pageQuery) {
//        return fenyeService.findPage(pageQuery);
//    }

    @RequestMapping(value="/findPage")
    //@ResponseBody
    public String list(HttpSession session , ModelMap model, productEntity productEntity,
                         @RequestParam(value = "pageCode", required = false, defaultValue = "1") int pageCode,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "16") int pageSize){

        model.addAttribute("pages",fenyeService.findByPage(productEntity,pageCode, pageSize));
        //return fenyeService.findByPage(productEntity,pageCode, pageSize);
        //return "test";
        return "index";
    }


    //一开始默认就访问
    @Controller
    @RequestMapping("/")
    public class index {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String index(ModelMap model,productEntity productEntity){
            int pageCode=1;
            int pageSize=16;
            model.addAttribute("pages",fenyeService.findByPage(productEntity,pageCode, pageSize));
            return "index";
        }
    }


}














//<table class="table table-striped">
//<thead>
//<tr>
//<th>分类编码</th>
//<th>名称</th>
//<th>时间</th>
//<th>详情</th>
//</tr>
//</thead>
//<tbody>
//<tr th:each="kind:${kinds}">
//<td th:text="${kind.kindCode}"></td>
//<td th:text="${kind.name}"></td>
//<td th:text="${kind.date}"></td>
//<td >详情</td>
//</tr>
//</tbody>
//</table>