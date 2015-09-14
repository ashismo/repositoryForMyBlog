package com.dao.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;
	static {
		try {
			sf = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Exception e){}
	}
	public static SessionFactory getSessionFactory() {
		return sf;
	}
}
