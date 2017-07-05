package com.uuzz.demo.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
* @ClassName: Demo  
* @Description: Demo实体类  
* @author zj 
* @date 2017年6月21日 上午10:36:06  
*
 */
public class Demo {

	/*主键*/
	private Integer id;
	
	/*编号*/
	private Integer no;
	/*名称*/
	private String name;
	
	/*创建时间*/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/*更新时间*/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Demo [id=" + id + ", no=" + no + ", name=" + name
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}			
}
