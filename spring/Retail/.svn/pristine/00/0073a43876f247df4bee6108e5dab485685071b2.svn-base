package com.org.coop.society.data.customer.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the security_questions database table.
 * 
 */
@Entity
@Table(name="security_questions")
@NamedQuery(name="SecurityQuestion.findAll", query="SELECT s FROM SecurityQuestion s")
@Where(clause="end_date is NULL")
public class SecurityQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private int questionId;

	@Column(name="create_date", updatable=false)
	private Timestamp createDate;

	@Column(name="create_user")
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String question;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="update_date", insertable=false)
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to UserSequrityQuestion
	@OneToMany(mappedBy="securityQuestion")
	private List<UserSecurityQuestion> userSequrityQuestions;

	public SecurityQuestion() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public List<UserSecurityQuestion> getUserSequrityQuestions() {
		return this.userSequrityQuestions;
	}

	public void setUserSequrityQuestions(List<UserSecurityQuestion> userSequrityQuestions) {
		this.userSequrityQuestions = userSequrityQuestions;
	}

	public UserSecurityQuestion addUserSequrityQuestion(UserSecurityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().add(userSequrityQuestion);
		userSequrityQuestion.setSecurityQuestion(this);

		return userSequrityQuestion;
	}

	public UserSecurityQuestion removeUserSequrityQuestion(UserSecurityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().remove(userSequrityQuestion);
		userSequrityQuestion.setSecurityQuestion(null);

		return userSequrityQuestion;
	}

}