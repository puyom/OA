package cn.gson.oasys.model.entity.process;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import cn.gson.oasys.model.entity.user.User;

@Entity
@Table(name="aoa_stay")
//项目小组表
public class Stay {

	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long projectId;

	@Column(name="user_name")
	private String userName;			//项目负责人


	@Column(name="emergency")
	private String emergency;			//紧急程度

	@Column(name="responsibility")
	private String responsibility;			//项目负责人

/*	@Column(name="members")
	private String members;    //项目成员及职能分工*/

	@Column(name="project_name")
	private String projectName;		//项目名称（起止-终止）


//	@Column(name="project_schedule")
//	private String projectSchedule;			//项目进度


//	@Column(name="start_time")
//	private Date startTime;			//流程开始时间
//
//	@Column(name="end_time")
//	private Date endTime;			//流程结束时间


	@Column(name="user_id")
	private Long userId;		//userId

	@Column(name="out_id")
	private Long outId;		//userId

	@Column(name="status")
	private Long status;		//逻辑删除


	@Column(name="team")
	private String team;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="burs",orphanRemoval = true)
	List<DetailsStay>  details;

	@OneToMany(mappedBy="burs2")
	List<Stay2>  stay2List;




	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	public List<Stay2> getStay2List() {
		return stay2List;
	}

	public void setStay2List(List<Stay2> stay2List) {
		this.stay2List = stay2List;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<DetailsStay> getDetails() {
		return details;
	}

	public void setDetails(List<DetailsStay> details) {
		this.details = details;
	}
}
