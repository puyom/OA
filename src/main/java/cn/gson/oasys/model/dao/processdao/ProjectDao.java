/*
package cn.gson.oasys.model.dao.processdao;

import cn.gson.oasys.model.entity.process.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProjectDao extends PagingAndSortingRepository<Project, Long>{
	
	//根据申请人查找流程
	@Query("select pro from Project as pro where pro.userId.userId=?1 order by pro.applyTime desc")
	Page<Project> findByuserId(Long userid, Pageable pa);

	//根据id查找姓名
	@Query("select userName from User where userId=?1 ")
	String findById(Long userid);

	//根据申请人查找流程
	@Query("select pro from Project as pro  order by pro.applyTime desc")
	List<Project> findAll();

	//根据申请人和审核人查找流程
	@Query(nativeQuery=true,value="select * from aoa_process_list  where aoa_process_list.process_user_id=?1 ORDER BY aoa_process_list.apply_time DESC LIMIT 0,3")
	List<Project> findlastthree(long userid);
	//根据状态和申请人查找流程
	@Query("select pro from Project as pro where pro.userId.userId=?1 and pro.statusId=?2 order by pro.applyTime desc")
	Page<Project> findByuserIdandstatus(Long userid, Long statusId, Pageable pa);
	
	//根据审核人，类型，标题模糊查询
	@Query("select pro from Project as pro where pro.userId.userId=?1 and (pro.typeNmae like %?2% or pro.processName like %?2% or pro.shenuser like %?2%) order by pro.applyTime desc")
	Page<Project> findByuserIdandstr(Long userid, String val, Pageable pa);

	@Query("select pro from Project as pro where pro.userId.userId=?1 and pro.processId=?2")
	Project findbyuseridandtitle(Long userid, Long proid);
	
}
*/
