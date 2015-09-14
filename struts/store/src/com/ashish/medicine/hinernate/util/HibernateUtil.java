package com.ashish.medicine.hinernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//	private static SessionFactory sf;
//	static {
//		try {
//			sf = new AnnotationConfiguration().configure().buildSessionFactory();
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//	public static SessionFactory getSessionFactory() {
//		return sf;
//	}
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 
	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
