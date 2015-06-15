package com.ashish.jms.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ashish.jms.JMSUtil;

/** This example shows how to establish a connection
* and send messages to the JMS queue.
*
*/
public class JMSSender
{
 

 private QueueConnectionFactory qconFactory;
 private QueueConnection qcon;
 private QueueSession qsession;
 private QueueSender qsender;
 private Queue queue;
 private TextMessage msg;

 /**
  * Creates all the necessary objects for sending
  * messages to a JMS queue.
  */
 public void init(Context ctx, String queueName)
    throws NamingException, JMSException
 {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMSUtil.JMS_CONNECTION_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qsender = qsession.createSender(queue);
    msg = qsession.createTextMessage();
    qcon.start();
 }

 /**
  * Sends a message to a JMS queue.
  *
  * @param message  message to be sent
  * @exception JMSException if JMS fails to send message due to internal error
  */
 public void send(String message) throws JMSException {
    msg.setText(message);
    qsender.send(msg);
 }

 /**
  * Closes JMS objects.
  * @exception JMSException if JMS fails to close objects due to internal error
  */
 public void close() throws JMSException {
    qsender.close();
    qsession.close();
    qcon.close();
 }

 public static void main(String[] args) throws Exception {
    InitialContext ic = JMSUtil.getInitialContext();
    JMSSender qs = new JMSSender();
    qs.init(ic, JMSUtil.JMS_QUEUE);
    readAndSend(qs);
    qs.close();
 }

 private static void readAndSend(JMSSender qs)
    throws IOException, JMSException
 {
    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
    String line=null;
    boolean quitNow = false;
    do {
     System.out.print("Enter message (\"quit\" to quit): \n");
     line = msgStream.readLine();
     if (line != null && line.trim().length() != 0) {
       qs.send(line);
       System.out.println("JMS Message Sent: "+line+"\n");
       quitNow = line.equalsIgnoreCase("quit");
     }
    } while (! quitNow);

 }
}