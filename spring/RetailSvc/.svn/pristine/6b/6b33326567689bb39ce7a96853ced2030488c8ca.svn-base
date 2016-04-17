package com.org.coop.retail.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the gl_master database table.
 * 
 */
@Entity
@Table(name="gl_master")
@NamedQuery(name="GlMaster.findAll", query="SELECT g FROM GlMaster g")
public class GlMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gl_mas_code")
	private int glMasCode;

	@Column(name="annexure_id")
	private int annexureId;

	@Column(name="gl_mas_desc")
	private String glMasDesc;

	//bi-directional many-to-one association to GlSubHeader
	@ManyToOne
	@JoinColumn(name="gl_sub_header_code")
	private GlSubHeader glSubHeader;

	//bi-directional many-to-one association to GlLedgerDtl
	@OneToMany(mappedBy="glMaster", fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<GlLedgerDtl> glLedgerDtls;

	public GlMaster() {
	}

	public int getGlMasCode() {
		return this.glMasCode;
	}

	public void setGlMasCode(int glMasCode) {
		this.glMasCode = glMasCode;
	}

	public int getAnnexureId() {
		return this.annexureId;
	}

	public void setAnnexureId(int annexureId) {
		this.annexureId = annexureId;
	}

	public String getGlMasDesc() {
		return this.glMasDesc;
	}

	public void setGlMasDesc(String glMasDesc) {
		this.glMasDesc = glMasDesc;
	}

	public GlSubHeader getGlSubHeader() {
		return this.glSubHeader;
	}

	public void setGlSubHeader(GlSubHeader glSubHeader) {
		this.glSubHeader = glSubHeader;
	}

	public List<GlLedgerDtl> getGlLedgerDtls() {
		return this.glLedgerDtls;
	}

	public void setGlLedgerDtls(List<GlLedgerDtl> glLedgerDtls) {
		this.glLedgerDtls = glLedgerDtls;
	}

	public GlLedgerDtl addGlLedgerDtl(GlLedgerDtl glLedgerDtl) {
		getGlLedgerDtls().add(glLedgerDtl);
		glLedgerDtl.setGlMaster(this);

		return glLedgerDtl;
	}

	public GlLedgerDtl removeGlLedgerDtl(GlLedgerDtl glLedgerDtl) {
		getGlLedgerDtls().remove(glLedgerDtl);
		glLedgerDtl.setGlMaster(null);

		return glLedgerDtl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + glMasCode;
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
		GlMaster other = (GlMaster) obj;
		if (glMasCode != other.glMasCode)
			return false;
		return true;
	}

}