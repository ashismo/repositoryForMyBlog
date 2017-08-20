package com.ashish.learning.v4.spring.expression.language;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ashish.learning.v4.FourWheelers;
import com.ashish.learning.v4.ShowRoom;

@Service
public class SpELServices {
	
	@Value("#{'${welcome.note}'?.toUpperCase()}") 
	private String welcomeNote;
	
	@Value("#{showRoom}")
	private ShowRoom showroom;
	
	// If the shwRoom is not null then the fourWheelers would get injected
	@Value("#{showRoom?.fourWheelers}")
	private FourWheelers fourWheelers;

	@Value("#{T(java.lang.Math).random()}")
	private String randomSpeed;
	
	public String getWelcomeNote() {
		return welcomeNote;
	}

	public void setWelcomeNote(String welcomeNote) {
		this.welcomeNote = welcomeNote;
	}

	public ShowRoom getShowroom() {
		return showroom;
	}

	public void setShowroom(ShowRoom showroom) {
		this.showroom = showroom;
	}

	public FourWheelers getFourWheelers() {
		return fourWheelers;
	}

	public void setFourWheelers(FourWheelers fourWheelers) {
		this.fourWheelers = fourWheelers;
	}

	public String getRandomSpeed() {
		return randomSpeed;
	}

	public void setRandomSpeed(String randomSpeed) {
		this.randomSpeed = randomSpeed;
	}
}
