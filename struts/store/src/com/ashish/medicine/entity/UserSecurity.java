package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_security database table.
 * 
 */
@Entity
@Table(name="user_security")
public class UserSecurity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_SEQ_ID")
	private int userSeqId;

	@Column(name="ANSWER")
	private String answer;

	@Column(name="DB_ADD_TS")
	private Timestamp dbAddTs;

	@Column(name="DB_ADD_USER")
	private int dbAddUser;

	@Column(name="DB_UPD_TS")
	private Timestamp dbUpdTs;

	@Column(name="DB_UPD_USER")
	private int dbUpdUser;

	@Column(name="QUESTION")
	private String question;

	//bi-directional many-to-one association to SequrityQuestion
    @ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private SequrityQuestion sequrityQuestion;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

    public UserSecurity() {
    }

	public int getUserSeqId() {
		return this.userSeqId;
	}

	public void setUserSeqId(int userSeqId) {
		this.userSeqId = userSeqId;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public SequrityQuestion getSequrityQuestion() {
		return this.sequrityQuestion;
	}

	public void setSequrityQuestion(SequrityQuestion sequrityQuestion) {
		this.sequrityQuestion = sequrityQuestion;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}