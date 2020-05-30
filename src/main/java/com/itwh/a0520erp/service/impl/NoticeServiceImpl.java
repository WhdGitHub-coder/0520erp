package com.itwh.a0520erp.service.impl;

import com.itwh.a0520erp.domain.Notice;
import com.itwh.a0520erp.mapper.NoticeMapper;
import com.itwh.a0520erp.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-17
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
