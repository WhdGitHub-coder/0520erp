package com.itwh.a0520erp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itwh.a0520erp.common.DataGridView;
import com.itwh.a0520erp.common.ResultObj;
import com.itwh.a0520erp.common.TreeNode;
import com.itwh.a0520erp.domain.Dept;
import com.itwh.a0520erp.service.DeptService;
import com.itwh.a0520erp.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /*
    加载部门管理左边的部门树的json
     */
    @RequestMapping("loadDeptManagerLeftTreeJson")
    public DataGridView loadDeptManagerLeftTreeJson(DeptVo deptVo){
        List<Dept> list = this.deptService.list();
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Dept dept : list) {
            Boolean spread=dept.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(dept.getId(),dept.getPid(),dept.getTitle(),spread));
        }
        return new DataGridView(treeNodes);
    }

    /*
    查询，加载数据
     */
    @RequestMapping("loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo){
        /*
        public Page(long current, long size) {
        this(current, size, 0L);
        }
         */
        IPage<Dept> page=new Page<>(deptVo.getPage(),deptVo.getLimit());
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getAddress()),"address",deptVo.getAddress());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getRemark()),"remark",deptVo.getRemark());
        //id来自deptRight299行，id和这个id里的pid，能匹配数据库的信息，就查出来
        queryWrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());
        queryWrapper.orderByAsc("ordernum");
        IPage<Dept> page1 = this.deptService.page(page, queryWrapper);
        return  new DataGridView(page1.getTotal(),page1.getRecords());
    }
    /**
     * 添加
     * @param deptVo
     * @return
     */
    //deptRight.html217行
    @RequestMapping("addDept")
    public ResultObj addDept(DeptVo deptVo) {
        try {
            deptVo.setCreatetime(new Date());
            this.deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /*
    加载最大的排序码
     */
    //deptRight.html219行初始化排序码
    @RequestMapping("loadDeptMaxOrderNum")
    public Map<String,Object> loadDeptMaxOrderNum(){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        //按照排序码降序查询所有部门
        List<Dept> list = this.deptService.list(queryWrapper);
        if(list.size()>0){
            //获取第一个，就是排序码最大值再加1
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }
    /**
     * 修改
     * @param deptVo
     * @return
     */
    @RequestMapping("updateDept")
    public ResultObj updateDept(DeptVo deptVo) {
        try {
            this.deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询当前的ID的部门有没有子部门
     */
    @RequestMapping("checkDeptHasChildrenNode")
    public Map<String,Object> checkDeptHasChildrenNode(DeptVo deptVo){
        Map<String,Object> map=new HashMap<>();
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",deptVo.getId());
        List<Dept> list = this.deptService.list(queryWrapper);
        if (list.size()>0){
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }
    /**
     * 删除
     * @param deptVo
     * @return
     */
    @RequestMapping("deleteDept")
    public ResultObj deleteDept(DeptVo deptVo) {
        try {
            this.deptService.removeById(deptVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

