package cloud.igoldenbeta.hippo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.igoldenbeta.hippo.bean.ServiceRouteBean;
import cloud.igoldenbeta.hippo.mapper.ServiceRouteMapper;
import cloud.igoldenbeta.hippo.service.ServiceRouteService;
import cloud.igoldenbeta.hippo.utils.BeanAndDtoTransfer;

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
