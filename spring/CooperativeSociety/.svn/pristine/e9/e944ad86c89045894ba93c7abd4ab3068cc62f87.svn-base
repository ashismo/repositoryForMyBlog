package com.org.coop.society.data.admin.entities;

import java.io.Serializable;
import javax.persistence.*;
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
public class SecurityQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private int questionId;

	@Column(name="create_date")
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

	@Column(name="update_date")
	private Timestamp updateDate;

	@Column(name="update_user")
	private String updateUser;

	//bi-directional many-to-one association to UserSequrityQuestion
	@OneToMany(mappedBy="securityQuestion")
	private List<UserSequrityQuestion> userSequrityQuestions;

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

	public List<UserSequrityQuestion> getUserSequrityQuestions() {
		return this.userSequrityQuestions;
	}

	public void setUserSequrityQuestions(List<UserSequrityQuestion> userSequrityQuestions) {
		this.userSequrityQuestions = userSequrityQuestions;
	}

	public UserSequrityQuestion addUserSequrityQuestion(UserSequrityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().add(userSequrityQuestion);
		userSequrityQuestion.setSecurityQuestion(this);

		return userSequrityQuestion;
	}

	public UserSequrityQuestion removeUserSequrityQuestion(UserSequrityQuestion userSequrityQuestion) {
		getUserSequrityQuestions().remove(userSequrityQuestion);
		userSequrityQuestion.setSecurityQuestion(null);

		return userSequrityQuestion;
	}

}