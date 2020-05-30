package com.itwh.a0520erp.service.impl;

import com.itwh.a0520erp.domain.Customer;
import com.itwh.a0520erp.mapper.CustomerMapper;
import com.itwh.a0520erp.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-23
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
