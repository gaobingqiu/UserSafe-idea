package com.table.records.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 此实体的定义应用了springmvc的注解，省去了传统的Hibernate跟数据库之间的映射配置文件
 * @author gbq
 */
@Entity
@Table(name = "records")
public class  Records implements Serializable {
    /**
     * 2016年3月3日
     */
    private static final long serialVersionUID = 1L;

    //指定主键
    @Id  
    //主键自动增长型
    @GeneratedValue 
    @Column(name="Id",length = 11)
    private String id;

    @Column(name="web_id",length = 25)
    private String webId;
    
    @Column(name="user_id",length = 25)
    private String userId;

    @Column(name="name",length = 30)
    private String name;
    
    @Column(length = 50)
    private String password;

    @Column(length = 50)
    private String link;

    @Column(length = 50)
    private String createTime;
    
    @Column(name="pass_count",length = 50)
    private String passCount;
    
    @Column(name="last_pass_time",length = 25)
    private String lastPassTime;
    
    @Column(length = 50)
    private String code;
    
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPassCount() {
		return passCount;
	}

	public void setPassCount(String passCount) {
		this.passCount = passCount;
	}

	public String getLastPassTime() {
		return lastPassTime;
	}

	public void setLastPassTime(String lastPassTime) {
		this.lastPassTime = lastPassTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
