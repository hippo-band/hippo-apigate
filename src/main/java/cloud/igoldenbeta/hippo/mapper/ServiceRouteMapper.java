package cloud.igoldenbeta.hippo.mapper;

import cloud.igoldenbeta.hippo.model.ServiceRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface ServiceRouteMapper {

	ServiceRoute selectById(Long id);

	Long insert(ServiceRoute serviceRoute);

	void updateByIdSelective(ServiceRoute serviceRoute);

	void updateById(ServiceRoute serviceRoute);

	void softDelete(Long id);

	List<ServiceRoute> selectBySelective(ServiceRoute serviceRoute);

//	@Select("SELECT * FROM tb_service_route")
	List<ServiceRoute> selectAll();

}
