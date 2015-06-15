package com.ashish.jms.receiver;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ashish.jms.JMSUtil;

/**
* This example shows how to establish a connection to
* and receive messages from a JMS queue. 
*/
public class JMSReceiver implements MessageListener
{
	 
 private QueueConnectionFactory qconFactory;
 private QueueConnection qcon;
 private QueueSession qsession;
 private QueueReceiver qreceiver;
 private Queue queue;
 private boolean quit = false;

/**
 * Message listener interface.
 * @param msg  message
 */
 public void onMessage(Message msg)
 {
    try {
     String msgText;
     if (msg instanceof TextMessage) {
       msgText = ((TextMessage)msg).getText();
     } else {
       msgText = msg.toString();
     }

     System.out.println("Message Received: "+ msgText );

     if (msgText.equalsIgnoreCase("quit")) {
       synchronized(this) {
         quit = true;
         this.notifyAll(); // Notify main thread to quit
       }
     }
    } catch (JMSException jmse) {
     System.err.println("An exception occurred: "+jmse.getMessage());
    }
 }

 /**
  * Creates required objects 
  */
 public void init(Context ctx, String queueName)
    throws NamingException, JMSException
 {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMSUtil.JMS_CONNECTION_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qreceiver = qsession.createReceiver(queue);
    qreceiver.setMessageListener(this);
    qcon.start();
 }

 /**
  * Closes JMS objects.
  */
 public void close()throws JMSException
 {
    qreceiver.close();
    qsession.close();
    qcon.close();
 }

 public static void main(String[] args) throws Exception {
    InitialContext ic = JMSUtil.getInitialContext();
    JMSReceiver qr = new JMSReceiver();
    qr.init(ic, JMSUtil.JMS_QUEUE);

    System.out.println(
        "JMS is ready To Receive Messages (To quit, send a \"quit\" message).");

    // Wait until a "quit" message has been received.
    synchronized(qr) {
	     while (! qr.quit) {
	       try {
	         qr.wait();
	       } catch (InterruptedException ie) {}
	     }
    }
    qr.close();
 }
}