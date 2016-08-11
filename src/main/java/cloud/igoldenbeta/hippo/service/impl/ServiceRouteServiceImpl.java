package cloud.igoldenbeta.hippo.service.impl;

import cloud.igoldenbeta.hippo.bean.ServiceRouteBean;
import cloud.igoldenbeta.hippo.mapper.ServiceRouteMapper;
import cloud.igoldenbeta.hippo.service.ServiceRouteService;
import cloud.igoldenbeta.hippo.utils.BeanAndDtoTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hanruofei on 16/8/11.
 */
@Service
public class ServiceRouteServiceImpl implements ServiceRouteService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRouteServiceImpl.class);

    @Autowired
    private ServiceRouteMapper serviceRouteMapper;

    @Override
    public List<ServiceRouteBean> showAll() {
        return BeanAndDtoTransfer.transOneListToAnoterList(serviceRouteMapper.selectAll(),ServiceRouteBean.class);
    }
}
