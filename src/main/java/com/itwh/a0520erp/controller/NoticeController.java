package com.itwh.a0520erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itwh.a0520erp.common.DataGridView;
import com.itwh.a0520erp.common.ResultObj;
import com.itwh.a0520erp.common.WebUtils;
import com.itwh.a0520erp.domain.Notice;
import com.itwh.a0520erp.domain.User;
import com.itwh.a0520erp.service.NoticeService;
import com.itwh.a0520erp.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    /*
    查询公告
     */
    //noticeManager.html145行加载公告内容
    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        IPage<Notice> page =new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        QueryWrapper<Notice> quearyWrapper = new QueryWrapper<>();
        quearyWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        quearyWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        quearyWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        quearyWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        quearyWrapper.orderByDesc("createtime");
        IPage<Notice> page1 = this.noticeService.page(page, quearyWrapper);
        return  new DataGridView(page1.getTotal(),page1.getRecords());
    }

    /*
    新增公告
     */
    //noticeManager211行，新增公告请求
    @RequestMapping("addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        try {
            //前端传过来的数据有title和content，要加上时间和人物
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            this.noticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /*
   修改公告
    */
    //noticeManager228行，修改公告请求
    @RequestMapping("updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try {
            this.noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    /*
    删除公告
     */
    @RequestMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id){
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /*
    批量删除
     */
    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteNotice(NoticeVo noticeVo){
        try {
            Collection<Serializable> idList=new ArrayList<>();
            for (Integer id : noticeVo.getIds()) {
                idList.add(id);
            }
            this.noticeService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

