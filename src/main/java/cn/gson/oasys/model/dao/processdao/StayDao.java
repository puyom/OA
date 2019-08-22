package cn.gson.oasys.model.dao.processdao;

import java.util.Date;
import java.util.List;

import cn.gson.oasys.model.entity.process.*;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StayDao extends PagingAndSortingRepository<Stay, Long> {

	//根据申请人查找流程
	@Query(nativeQuery=true,value="SELECT * from  aoa_stay  where aoa_stay.user_id=? and aoa_stay.status= '1' ")
	List<Stay> findByuserId(Long userid);


	//客户查找项目条
	@Query(nativeQuery=true,value="SELECT * from  aoa_stay  where aoa_stay.out_id=? and aoa_stay.status= '1'")
	List<Stay> findByoutId(Long userid);

	//查看项目详细
	@Query(nativeQuery=true,value="SELECT * from  aoa_stay  where aoa_stay.project_id=?")
	 Stay findByprojectId(Long projectId);


	//管理员查看所有流程
	@Query(nativeQuery=true,value="SELECT * from  aoa_stay where aoa_stay.status= '1' ")
	List<Stay> findAll();

	//逻辑删除
	@Query(nativeQuery=true,value="update aoa_stay set aoa_stay.status= '2' where aoa_stay.project_id=?")
	@Modifying
	void updateStay(Long projectId);

	/*@Query(nativeQuery=true,value="insert into aoa_stay (project_id,item, user_name, members, project_name, project_schedule, start_time.end_time,user_id) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)")
	@Modifying
	void insert2(int projectId, String item,String userName, String members, String projectName, String projectSchedule, Date startTime,Date endTime, int userId);*/
}
