package com.junhao.yiqi.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "image")
public class ImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue  用这条报了一个错  ERROR org.hibernate.id.enhanced.TableStructure - could not read a hi value
    //java.sql.SQLSyntaxErrorException: Table 'yiqi.hibernate_sequence' doesn't exist
    @Column(name = "id")
    private Integer id;

    @Column(name = "url")
    private String url;//http下载路径

    @Column(name = "absolute_url")
    private String absolute_url;//硬盘的路径

    @Column(name = "md5")
    private String md5;//校验上传的码

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "size")
    private String size;

    @Column(name = "create_time")
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbsolute_url() {
        return absolute_url;
    }

    public void setAbsolute_url(String absolute_url) {
        this.absolute_url = absolute_url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
