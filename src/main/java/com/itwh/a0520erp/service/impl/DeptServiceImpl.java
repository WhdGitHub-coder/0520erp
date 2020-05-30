package com.itwh.a0520erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwh.a0520erp.domain.Dept;
import com.itwh.a0520erp.mapper.DeptMapper;
import com.itwh.a0520erp.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-18
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    //做缓存
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean updateById(Dept entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean save(Dept entity) {
        return super.save(entity);
    }
}
