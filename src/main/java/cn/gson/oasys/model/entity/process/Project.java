/*
package cn.gson.oasys.model.entity.process;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="aoa_ProjectList")
public class Project {

    @Id
    @Column(name="project_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long projectId;

    @Column(name="item")
    private String item ;			//项目类

    @Column(name="userName")
    private Long userName;			//项目负责人

    @Column(name="members")
    private Long members;    //项目成员及职能分工


    @Column(name="project_name")
    private String projectName;		//项目名称（起止-终止）


    @Column(name="project_schedule")
    private String projectSchedule;			//项目进度

    @Column(name="complete")
    private String  complete;		//完成

    @Column(name="unfinished")
    private String  unfinished;		//未完成

    @Column(name="point")
    private String  point;		//未完成原因，困难点

    @Column(name="coordination")
    private String  coordination;		//需协同

    @Column(name="start_time")
    private Date startTime;			//流程开始时间

    @Column(name="end_time")
    private Date endTime;			//流程结束时间

    @Column(name="procsee_days")
    private Double procseeDays;		//流程总天数

    @Column(name="userId")
    private Double userId;		//userId

    public Double getUserId() {
        return userId;
    }

    public void setUserId(Double userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSchedule() {
        return projectSchedule;
    }

    public void setProjectSchedule(String projectSchedule) {
        this.projectSchedule = projectSchedule;
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

    public Double getProcseeDays() {
        return procseeDays;
    }

    public void setProcseeDays(Double procseeDays) {
        this.procseeDays = procseeDays;
    }
}
*/
