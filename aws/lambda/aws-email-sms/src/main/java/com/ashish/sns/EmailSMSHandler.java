package com.ashish.sns;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.ashish.sns.dto.RequestBean;
import com.ashish.sns.dto.ResponseBean;

public class EmailSMSHandler implements RequestHandler<RequestBean, ResponseBean> {

	@Override
	public ResponseBean handleRequest(RequestBean request, Context context) {
		context.getLogger().log("Input: " + request);
		ResponseBean response = new ResponseBean();
		if (request != null) {
			String recipient = request.getRecipient();
			String otp = request.getMessage();
			String senderId = request.getSenderId();
			
			AmazonSNSClient snsClient = new AmazonSNSClient();
			Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
			// <set SMS attributes>
			smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
			        .withStringValue("NETWRE") //The sender ID shown on the device.
			        .withDataType("String"));
			smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
			        .withStringValue("10.0") //Sets the max price to 10 USD.
			        .withDataType("Number"));
			smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
			        .withStringValue("Transactional") //Sets the type to Transactional.
			        .withDataType("String"));
			if(recipient != null){
				if(recipient.trim().startsWith("+")) {
					sendSMSMessage(snsClient, smsAttributes, request, response, context);
				} else {
					sendEmail(request, response, context);
				}
			}
		}
		return response;
	}

	public void sendSMSMessage(AmazonSNSClient snsClient, 
			Map<String, MessageAttributeValue> smsAttributes, RequestBean request, ResponseBean response, Context context) {
		
		try {
			System.out.println(smsAttributes);
			PublishResult result = snsClient.publish(new PublishRequest().withMessage("Your OTP is: " + request.getMessage()).withPhoneNumber(request.getRecipient().trim())
					.withMessageAttributes(smsAttributes));
			System.out.println(result); // Prints the message ID.
			 response.setCode("100");
			response.setResult(result);
		} catch (Exception ex) {
			System.out.println("The SMS was not sent. Error message: " + ex.getMessage());
		      context.getLogger().log("The SMS was not sent. Error message: " + ex.getMessage());
		      response.setResponse("The SMS was not sent. Error message: "  + ex.getMessage());
		      response.setCode("-100");
		}
	}
	public void sendEmail(RequestBean request, ResponseBean response, Context context) {
		try {
		      AmazonSimpleEmailService client = 
		          AmazonSimpleEmailServiceClientBuilder.standard()
		          // Replace US_WEST_2 with the AWS Region you're using for
		          // Amazon SES.
		            .withRegion(Regions.US_WEST_2).build();
		      SendEmailRequest emailrequest = new SendEmailRequest()
		          .withDestination(
		              new Destination().withToAddresses(request.getRecipient().trim()))
		          .withMessage(new Message()
		              .withBody(new Body()
		                  .withHtml(new Content()
		                      .withCharset("UTF-8").withData("Your OTP is: " + request.getMessage()))
		                  .withText(new Content()
		                      .withCharset("UTF-8").withData("Your OTP is: " + request.getMessage())))
		              .withSubject(new Content()
		                  .withCharset("UTF-8").withData(request.getSubject())))
		          .withSource(request.getSenderId());
		          // Comment or remove the next line if you are not using a
		          // configuration set
//		          .withConfigurationSetName(CONFIGSET);
		      client.sendEmail(emailrequest);
		      System.out.println("Email sent to: " + request.getRecipient());
		      response.setCode("100");
		      response.setResponse("Email sent to " + request.getRecipient());
		    } catch (Exception ex) {
		      System.out.println("The email was not sent. Error message: " + ex.getMessage());
		      context.getLogger().log("The email was not sent. Error message: " + ex.getMessage());
		      response.setResponse("The email was not sent. Error message: "  + ex.getMessage());
		      response.setCode("-100");
		    }
	}

}
