package com.junhao.yiqi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junhao.yiqi.dao.BrandMapper;
import com.junhao.yiqi.dao.ImgMapper;
import com.junhao.yiqi.entity.PageBean;
import com.junhao.yiqi.entity.PageRequest;
import com.junhao.yiqi.entity.PageResult;
import com.junhao.yiqi.entity.productEntity;
import com.junhao.yiqi.service.fenyeService;
import com.junhao.yiqi.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("fenyeService")
public class fenyeServiceImpl implements fenyeService {

    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {  //PageRequest pageRequest
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @return
     */
    private PageInfo<productEntity> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<productEntity> sysMenus = imgMapper.gettp();
        return new PageInfo<productEntity>(sysMenus);
    }




    /**\
     * 分页
     * @param productEntity
     * @param pageCode   当前页
     * @param pageSize   每页 的记录数
     * @return
     */
    @Override
    public PageBean findByPage(productEntity productEntity, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode,pageSize);//使用Mybatis分页插件
        Page<productEntity> page = imgMapper.gettp2(productEntity);

        //计算总页数：总页数 = 总记录数 / 每页显示的记录条数。这里用到的ceil()方法：返回大于或登录参数的最小double值，并等于数学整数。如double a = 5;double b = 3;ceil(a/b) = 2.0。最后用Double类的intValue()方法返回此Double值作为int类型的值。
        return new PageBean(pageCode, (int)Math.ceil((double) (page.getTotal() / (double)pageSize)), (int)page.getTotal(),  pageSize , page.getResult());
    }

    /**\
     * 分页  16
     * @return
     */
    @Override
    public PageBean findByPage() {
        int pageCode = 1;
        int pageSize = 16;
        PageHelper.startPage(1,16);//使用Mybatis分页插件
        Page<productEntity> page = imgMapper.gettp3();



        //计算总页数：总页数 = 总记录数 / 每页显示的记录条数。这里用到的ceil()方法：返回大于或登录参数的最小double值，并等于数学整数。如double a = 5;double b = 3;ceil(a/b) = 2.0。最后用Double类的intValue()方法返回此Double值作为int类型的值。
        return new PageBean(pageCode, (int)Math.ceil((double) (page.getTotal() / (double)pageSize)), (int)page.getTotal(),  pageSize , page.getResult());
    }

    @Override
    public Map begin() {
        Map map = new HashMap();
        int pageCode = 1;
        int pageSize = 16;
        PageHelper.startPage(1,16);//使用Mybatis分页插件
        Page<productEntity> page = imgMapper.gettp3();
        int begin=0;
        int end=0;
        int totalPage = (int)Math.ceil((double) (page.getTotal() / (double)pageSize));
        if (pageCode<= 5) {
            begin=1;end=totalPage;
        }else {
            begin=pageCode - 1; end=pageCode+4;
            if (begin<1) {//头溢出
                begin=1;end=5;
            }
            if (end>totalPage){//尾溢出
                begin=pageCode-4;end=totalPage;
            }
        }

        map.put("begin",begin);
        map.put("end",end);

        return map;  //todo   1,18
    }

    @Override
    public List<String> allBrand() {
        return brandMapper.getAllBrand();
    }


}
