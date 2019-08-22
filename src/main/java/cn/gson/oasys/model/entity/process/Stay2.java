package cn.gson.oasys.model.entity.process;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="aoa_stay2")
//项目进度表
public class Stay2 {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;



	@Column(name="project_schedule")
	private String projectSchedule;			//项目进度


	@Column(name="start_time")
	private Date startTime;			//流程开始时间

	@Column(name="end_time")
	private Date endTime;			//流程结束时间

	@Column(name="user")
	private String  user;		//更新的人
/*	@Column(name="project_id")
	private Long projectId;   //对应的流程*/

	@ManyToOne()
	@JoinColumn(name="project_id")
	private Stay burs2;//对应项目小组表

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSchedule() {
		return projectSchedule;
	}

	public void setProjectSchedule(String projectSchedule) {
		this.projectSchedule = projectSchedule;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Stay getBurs2() {
		return burs2;
	}

	public void setBurs2(Stay burs2) {
		this.burs2 = burs2;
	}
}
