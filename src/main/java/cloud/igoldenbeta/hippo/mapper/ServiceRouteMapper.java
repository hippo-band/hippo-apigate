package cloud.igoldenbeta.hippo.mapper;

import java.util.List;

import cloud.igoldenbeta.hippo.model.ServiceRoute;

public interface ServiceRouteMapper {

	ServiceRoute selectById(Long id);

	Long insert(ServiceRoute serviceRoute);

	void updateByIdSelective(ServiceRoute serviceRoute);

	void updateById(ServiceRoute serviceRoute);

	void softDelete(Long id);

	List<ServiceRoute> selectBySelective(ServiceRoute serviceRoute);

	List<ServiceRoute> selectAll();

}
