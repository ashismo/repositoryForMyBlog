package com.ashish.jms;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSUtil {

	// JNDI context factory.
	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";

	// JMS context factory.
	public final static String JMS_CONNECTION_FACTORY = "jms/myConnectionFactory";

	// JMS queue.
	public final static String JMS_QUEUE = "jms/myJMSQueue";
	
	// JMS queue.
	public final static String URL = "t3://127.0.0.1:7001";

	public static InitialContext getInitialContext()
			throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, URL);
		return new InitialContext(env);
	}
}