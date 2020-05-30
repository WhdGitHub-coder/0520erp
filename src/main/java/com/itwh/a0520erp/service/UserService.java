package com.itwh.a0520erp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itwh.a0520erp.domain.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-12
 */
public interface UserService extends IService<User> {

    void saveUserRole(Integer uid, Integer[] ids);

}
