package cn.gson.oasys.model.dao.processdao;

import java.util.List;

import cn.gson.oasys.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.gson.oasys.model.entity.process.ProcessList;

public interface ProcessListDao extends PagingAndSortingRepository<ProcessList, Long>{
	
	//根据申请人查找流程
	@Query("select pro from ProcessList as pro where pro.userId.userId=?1 order by pro.applyTime desc")
	Page<ProcessList> findByuserId(Long userid,Pageable pa);



	//根据id查找姓名
	@Query("select userName from User where userId=?1 ")
	String findById(Long userid);

	//根据申请人查找流程
	@Query("select pro from ProcessList as pro  order by pro.applyTime desc")
	List<ProcessList> findAll();

	//根据申请人和审核人查找流程
	@Query(nativeQuery=true,value="select * from aoa_process_list  where aoa_process_list.process_user_id=?1 ORDER BY aoa_process_list.apply_time DESC LIMIT 0,3")
	List<ProcessList> findlastthree(long userid);

	//根据状态和申请人查找流程
	@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and pro.statusId=?2 order by pro.applyTime desc")
	Page<ProcessList> findByuserIdandstatus(Long userid, Long statusId, Pageable pa);

	//admin根据状态和申请人查找流程
	@Query("select pro from ProcessList as pro where pro.userId.userId=?1  order by pro.applyTime desc")
	List<ProcessList> findByuserIdandstatus2(Long userid);
	
	//根据审核人，类型，标题模糊查询
	@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and (pro.typeNmae like %?2% or pro.processName like %?2% or pro.shenuser like %?2%) order by pro.applyTime desc")
	Page<ProcessList> findByuserIdandstr(Long userid, String val, Pageable pa);

	//admin根据审核人，类型，标题模糊查询
	@Query("select pro from ProcessList as pro where  (pro.typeNmae like %?1% or pro.processName like %?1% or pro.shenuser like %?1%) order by pro.applyTime desc")
	List<ProcessList> findByuserIdandstr2( String val);


	@Query("select pro from ProcessList as pro where pro.userId.userId=?1 and pro.processId=?2")
	ProcessList findbyuseridandtitle(Long userid,Long proid);
	
}
