package com.ashish.medicine.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the sequrity_questions database table.
 * 
 */
@Entity
@Table(name="sequrity_questions")
public class SequrityQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QUESTION_ID")
	private int questionId;

	@Column(name="QUESTION")
	private String question;

	//bi-directional many-to-one association to UserSecurity
	@OneToMany(mappedBy="sequrityQuestion")
	private Set<UserSecurity> userSecurities;

    public SequrityQuestion() {
    }

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<UserSecurity> getUserSecurities() {
		return this.userSecurities;
	}

	public void setUserSecurities(Set<UserSecurity> userSecurities) {
		this.userSecurities = userSecurities;
	}
	
}