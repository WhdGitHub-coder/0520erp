package com.itwh.a0520erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwh.a0520erp.common.WebUtils;
import com.itwh.a0520erp.domain.Goods;
import com.itwh.a0520erp.domain.Inport;
import com.itwh.a0520erp.domain.Outport;
import com.itwh.a0520erp.domain.User;
import com.itwh.a0520erp.mapper.GoodsMapper;
import com.itwh.a0520erp.mapper.InportMapper;
import com.itwh.a0520erp.mapper.OutportMapper;
import com.itwh.a0520erp.service.OutportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 翁煌
 * @since 2020-05-25
 */
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Autowired
    private InportMapper inportMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addOutPort(Integer id, Integer number, String remark) {
        //1,根据进货单ID查询进货单信息
        Inport inport = this.inportMapper.selectById(id);
        //2,根据商品ID查询商品信息
        Goods goods = this.goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        this.goodsMapper.updateById(goods);
        //3,添加退货单信息
        Outport entity=new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setNumber(number);
        User user=(User) WebUtils.getSession().getAttribute("user");
        entity.setOperateperson(user.getName());
        entity.setOutportprice(inport.getInportprice());
        entity.setOutputtime(new Date());
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        this.getBaseMapper().insert(entity);
    }

}
