package cn.gson.oasys.model.dao.plandao;

import cn.gson.oasys.model.entity.process.DetailsStay;
import cn.gson.oasys.model.entity.process.Stay;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DetailsStayDao extends PagingAndSortingRepository<DetailsStay, Long> {


	@Query(nativeQuery=true,value="SELECT * from  aoa_detailstay where aoa_detailstay.project_id =?")
	List<DetailsStay> find(int projectId);

}
