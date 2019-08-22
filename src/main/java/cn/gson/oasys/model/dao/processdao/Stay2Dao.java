package cn.gson.oasys.model.dao.processdao;

import cn.gson.oasys.model.entity.process.Stay;
import cn.gson.oasys.model.entity.process.Stay2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface Stay2Dao extends PagingAndSortingRepository<Stay2, Long> {



    @Query(nativeQuery=true,value="SELECT * from  aoa_stay2  where aoa_stay2.project_id=?")
    List<Stay2> findByprojectId(Long projectId);
}