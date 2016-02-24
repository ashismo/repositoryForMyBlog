package com.org.coop.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.UserSecurityBean;
import com.org.coop.society.data.admin.entities.SecurityQuestion;
import com.org.coop.society.data.admin.entities.User;
import com.org.coop.society.data.admin.entities.UserSecurityQuestion;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;
import com.org.coop.society.data.admin.repositories.UserAdminRepository;

@Component("userSecuritiesCC")
public class UserSecuritiesCC extends DozerConverter<UserSecurityQuestion, UserSecurityBean> implements MapperAware, CustomConverter {
	
	@Autowired
	private UserAdminRepository userRepository;
	
	@Autowired
	private SecurityQuestionRepository userSecurityQuestionRepository;
	
	public UserSecuritiesCC() {
		super(UserSecurityQuestion.class, UserSecurityBean.class);
	}
	
	public UserSecuritiesCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserSecurityQuestion convertFrom(UserSecurityBean src, UserSecurityQuestion dest) {
		if(src != null) {
				User user = userRepository.findOne(src.getUserId());
				SecurityQuestion securityQuestion = userSecurityQuestionRepository.findOne(src.getQuestionId());
				if(user != null && securityQuestion != null) {
					dest.setUser(user);
					dest.setSecurityQuestion(securityQuestion);
				}
			}
		return dest;
	}

	@Override
	public UserSecurityBean convertTo(UserSecurityQuestion src, UserSecurityBean dest) {
		if(src != null) {
			dest.setUserId(src.getUser().getUserId());
			dest.setQuestionId(src.getSecurityQuestion().getQuestionId());
		}
		return dest;
	}
}
