package com.itwh.a0520erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itwh.a0520erp.domain.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-12
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户ID删除用户角色中间表的数据
     * @param id
     */
    void deleteRoleUserByUid(Serializable id);

    /**
     * 保存角色和用户的关系
     * @param uid
     * @param rid
     */
    void insertUserRole(@Param("uid") Integer uid,@Param("rid") Integer rid);
}
