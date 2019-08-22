package cn.gson.oasys.model.entity.process;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="aoa_detailstay")
//项目小组明细表
public class DetailsStay {

	@Id
	@Column(name="detailstay_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long detailstayId;

	@Column(name="start_time")
	private Date StartTime;			//流程修改时间



	@Column(name="complete")
	private String  complete;		//完成

	@Column(name="unfinished")
	private String  unfinished;		//未完成

	@Column(name="point")
	private String  point;		//未完成原因，困难点

	@Column(name="coordination")
	private String  coordination;		//需协同

	@Column(name="user")
	private String  user;		//更新的人
	
	@ManyToOne()
	@JoinColumn(name="project_id")
	private Stay burs;//对应项目小组表



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getDetailstayId() {
		return detailstayId;
	}

	public void setDetailstayId(Long detailstayId) {
		this.detailstayId = detailstayId;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(String unfinished) {
		this.unfinished = unfinished;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getCoordination() {
		return coordination;
	}

	public void setCoordination(String coordination) {
		this.coordination = coordination;
	}

	public Stay getBurs() {
		return burs;
	}

	public void setBurs(Stay burs) {
		this.burs = burs;
	}
}
