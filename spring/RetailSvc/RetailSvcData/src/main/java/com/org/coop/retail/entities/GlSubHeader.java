package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the gl_sub_header database table.
 * 
 */
@Entity
@Table(name="gl_sub_header")
@NamedQuery(name="GlSubHeader.findAll", query="SELECT g FROM GlSubHeader g")
public class GlSubHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gl_sub_header_code")
	private int glSubHeaderCode;

	@Column(name="gl_sub_header_desc")
	private String glSubHeaderDesc;

	//bi-directional many-to-one association to GlMaster
	@OneToMany(mappedBy="glSubHeader", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<GlMaster> glMasters;

	//bi-directional many-to-one association to GlHeader
	@ManyToOne
	@JoinColumn(name="gl_header_code")
	private GlHeader glHeader;

	public GlSubHeader() {
	}

	public int getGlSubHeaderCode() {
		return this.glSubHeaderCode;
	}

	public void setGlSubHeaderCode(int glSubHeaderCode) {
		this.glSubHeaderCode = glSubHeaderCode;
	}

	public String getGlSubHeaderDesc() {
		return this.glSubHeaderDesc;
	}

	public void setGlSubHeaderDesc(String glSubHeaderDesc) {
		this.glSubHeaderDesc = glSubHeaderDesc;
	}

	public List<GlMaster> getGlMasters() {
		return this.glMasters;
	}

	public void setGlMasters(List<GlMaster> glMasters) {
		this.glMasters = glMasters;
	}

	public GlMaster addGlMaster(GlMaster glMaster) {
		getGlMasters().add(glMaster);
		glMaster.setGlSubHeader(this);

		return glMaster;
	}

	public GlMaster removeGlMaster(GlMaster glMaster) {
		getGlMasters().remove(glMaster);
		glMaster.setGlSubHeader(null);

		return glMaster;
	}

	public GlHeader getGlHeader() {
		return this.glHeader;
	}

	public void setGlHeader(GlHeader glHeader) {
		this.glHeader = glHeader;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + glSubHeaderCode;
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
		GlSubHeader other = (GlSubHeader) obj;
		if (glSubHeaderCode != other.glSubHeaderCode)
			return false;
		return true;
	}

}