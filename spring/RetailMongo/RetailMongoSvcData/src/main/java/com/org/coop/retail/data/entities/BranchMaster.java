package com.org.coop.retail.data.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "branchMaster")
public class BranchMaster {
	
	@Transient
	@JsonIgnore
	private ObjectId id;
	
	@Id
	private int branchId;
	
	private String phone1;
	
	@Field
	private String branchName;
	
	@com.org.coop.retail.data.entities.Timestamp
	private Date createDate;
	
	@com.org.coop.retail.data.entities.Timestamp
	private Date updateDate;
	
//	@DBRef
//	@CascadeSave
	private List<MaterialMaster> materials;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<MaterialMaster> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialMaster> materials) {
		this.materials = materials;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BranchMaster other = (BranchMaster) obj;
		if (branchId != other.branchId)
			return false;
		return true;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
	    updateDate = new Timestamp(System.currentTimeMillis());
	    if (createDate == null) {
	    	createDate = new Timestamp(System.currentTimeMillis());
	    }
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
