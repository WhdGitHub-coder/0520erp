package com.itwh.a0520erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwh.a0520erp.domain.User;
import com.itwh.a0520erp.mapper.UserMapper;
import com.itwh.a0520erp.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-12
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(User entity) {

        return super.updateById(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据用户ID删除用户角色中间表的数据
        UserMapper baseMapper = this.getBaseMapper();
        baseMapper.deleteRoleUserByUid(id);
        //删除用户头[如果是默认头像不删除  否则删除]
        return super.removeById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        this.getBaseMapper().deleteRoleUserByUid(uid);
        for (Integer rid : ids) {
            this.getBaseMapper().insertUserRole(uid,rid);
        }
    }
}
