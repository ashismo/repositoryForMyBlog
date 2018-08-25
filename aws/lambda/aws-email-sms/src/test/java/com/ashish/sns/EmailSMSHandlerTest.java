package com.ashish.sns;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.ashish.sns.EmailSMSHandler;
import com.ashish.sns.dto.RequestBean;
import com.ashish.sns.dto.ResponseBean;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class EmailSMSHandlerTest {

    private static RequestBean request = new RequestBean();

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
//        input[0] = "+16126448158";
        
        request.setSenderId("ashismo@gmail.com");
//        request.setSenderId("ASHISH");
        request.setMessage("12345678");
//        request.setRecipient("+16126");
        request.setRecipient("ashishkrmondal@gmail.com");
        request.setSubject("Your Login OTP");
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        EmailSMSHandler handler = new EmailSMSHandler();
        Context ctx = createContext();

        ResponseBean output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
