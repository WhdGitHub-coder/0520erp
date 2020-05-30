package com.itwh.a0520erp.service.impl;

import com.itwh.a0520erp.domain.Loginfo;
import com.itwh.a0520erp.mapper.LoginfoMapper;
import com.itwh.a0520erp.service.LoginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-15
 */
@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

}
