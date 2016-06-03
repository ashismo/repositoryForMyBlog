package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Where;


/**
 * The persistent class for the dropdown_master database table.
 * 
 */
@Entity
@Table(name="dropdown_master")
@NamedQuery(name="DropdownMaster.findAll", query="SELECT d FROM DropdownMaster d")
@Where(clause="delete_ind is NULL or delete_ind='N'")
public class DropdownMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String category;

	@Column(name="delete_ind")
	private String deleteInd;

	@Column(name="delete_reason")
	private String deleteReason;

	private String description;

	//bi-directional many-to-one association to InactiveDropdown
	@OneToMany(mappedBy="dropdownMaster", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@Where(clause = "delete_ind is null or delete_ind = 'N'")
	private List<InactiveDropdown> inactiveDropdowns;
		
	public DropdownMaster() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		DropdownMaster other = (DropdownMaster) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}