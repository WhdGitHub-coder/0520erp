package com.itwh.a0520erp.vo;

import com.itwh.a0520erp.domain.Loginfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false) //不使用父类的方法
public class LoginfoVo extends Loginfo {
    private static final long serialVersionUID=1L;

    //当前页
    private Integer page=1;
    //一页几条
    private Integer limit=10;

    private Integer[] ids;//接收多个ID,可用与批量删除


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
