package com.github.hippo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hippo.bean.ServiceRouteBean;
import com.github.hippo.mapper.ServiceRouteMapper;
import com.github.hippo.service.ServiceRouteService;
import com.github.hippo.utils.BeanAndDtoTransfer;

/**
 * Created by hanruofei on 16/8/11.
 */
@Service
public class ServiceRouteServiceImpl implements ServiceRouteService{
    @Autowired
    private ServiceRouteMapper serviceRouteMapper;

    @Override
    public List<ServiceRouteBean> showAll() {
        return BeanAndDtoTransfer.transOneListToAnoterList(serviceRouteMapper.selectAll(),ServiceRouteBean.class);
    }
}
