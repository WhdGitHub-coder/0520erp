package com.itwh.a0520erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itwh.a0520erp.common.DataGridView;
import com.itwh.a0520erp.common.ResultObj;
import com.itwh.a0520erp.domain.Loginfo;
import com.itwh.a0520erp.service.LoginfoService;
import com.itwh.a0520erp.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    /**
     * 全查询
     */
    //在loginfoManager.html 101行分页数据加载
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
        /*
        public Page(long current, long size) { current：当前页，size：一页几条
        this(current, size, 0L);
         }
         */
        IPage<Loginfo> page=new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper=new QueryWrapper<>();
        //StringUtils要导入lang3包
        //根据登录名称、ip模糊查询，开始-结束时间查询
        /*
        public Children like(boolean condition, R column, Object val) {
        return this.likeValue(condition, column, val, SqlLike.DEFAULT);
        }
         */
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip",loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        /*
        public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return this.baseMapper.selectPage(page, queryWrapper);
        }
         */
        IPage<Loginfo> page1 = this.loginfoService.page(page, queryWrapper);
        return new DataGridView(page1.getTotal(), page1.getRecords());
    }

    /*
    删除
     */
    //loginfoManager.html150行删除数据
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*
    批量删除
     */
    //loginfoManager.html176行批量删除数据a
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj deleteLoginfo(LoginfoVo loginfoVo){
        try {
            Collection<Serializable> idList=new ArrayList<>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.loginfoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

