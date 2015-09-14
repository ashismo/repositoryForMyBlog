package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the attachment database table.
 * 
 */
@NamedQueries ({
	@NamedQuery(name="viewAttachmentDetails",
			query="select at.attachmentId as attachmentId, " +
					"at.attachmentDesc as attachmentDesc, " +
					"at.fileName as filename, " +
					"at.transactionDetail.transactionDetailsId as transactionDetailsId " +
					"from Attachment at " +
					"where at.transactionDetail.transactionDetailsId = :transactionDetailsId " +
					"order by at.transactionDetail.transactionDetailsId asc "
	),
	@NamedQuery(name="getAttachmentDetailsByAttachmentId",
			query="select at from Attachment at " +
					"where at.attachmentId = :attachmentId "
	),
	@NamedQuery(name="getAttachmentDetailsByTransactionDetailsId",
			query="select at from Attachment at " +
					"where at.transactionDetail.transactionDetailsId = :transactionDetailsId "
	)
})
@Entity
@Table(name="attachment")
public class Attachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ATTACHMENT_ID", unique=true, nullable=false)
	private int attachmentId;

    @Lob()
	@Column(name="ATTACHED_FILE")
	private byte[] attachedFile;

	@Column(name="ATTACHMENT_DESC", length=500)
	private String attachmentDesc;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="FILE_CONTENT_TYPE", length=100)
	private String fileContentType;

	@Column(name="FILE_NAME", length=100)
	private String fileName;

	//bi-directional many-to-one association to TransactionDetail
    @ManyToOne
	@JoinColumn(name="TRANSACTION_DETAILS_ID")
	private TransactionDetails transactionDetail;

    public Attachment() {
    }

	public int getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public byte[] getAttachedFile() {
		return this.attachedFile;
	}

	public void setAttachedFile(byte[] attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getAttachmentDesc() {
		return this.attachmentDesc;
	}

	public void setAttachmentDesc(String attachmentDesc) {
		this.attachmentDesc = attachmentDesc;
	}

	public Timestamp getDbAddTs() {
		return this.dbAddTs;
	}

	public void setDbAddTs(Timestamp dbAddTs) {
		this.dbAddTs = dbAddTs;
	}

	public int getDbAddUser() {
		return this.dbAddUser;
	}

	public void setDbAddUser(int dbAddUser) {
		this.dbAddUser = dbAddUser;
	}

	public Timestamp getDbUpdTs() {
		return this.dbUpdTs;
	}

	public void setDbUpdTs(Timestamp dbUpdTs) {
		this.dbUpdTs = dbUpdTs;
	}

	public int getDbUpdUser() {
		return this.dbUpdUser;
	}

	public void setDbUpdUser(int dbUpdUser) {
		this.dbUpdUser = dbUpdUser;
	}

	public String getFileContentType() {
		return this.fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TransactionDetails getTransactionDetail() {
		return this.transactionDetail;
	}

	public void setTransactionDetail(TransactionDetails transactionDetail) {
		this.transactionDetail = transactionDetail;
	}
	
}