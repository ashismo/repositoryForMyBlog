package com.ashish.spring.cloud.ws;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.beans.DebitCardBean;
import com.ashish.business.DebitCardManagementImpl;


@RestController
@RequestMapping("/rest")
public class SpringCloudAppWS {
	private static final Logger logger = LoggerFactory.getLogger(SpringCloudAppWS.class);
	
	@Autowired
	private DebitCardManagementImpl debitCardManagementImpl;
	/**
	 * This method transfers balance from one account to another
	 * @return
	 */
	@ApiOperation(value="balanceTransfer", notes="Transfer balance from one account to another")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "toAccount", value = "1234567890", required = true, dataType = "string", paramType = "query", defaultValue="1234567890")
      })
	@RequestMapping(path="/balanceTransfer", method = RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public String balanceTransfer(@RequestParam(name="toAccount")String toAccount) {
		logger.info("Test");
		
		DebitCardBean dcBean = new DebitCardBean();
		dcBean.setFirstName("Ujan");
		dcBean.setLastName("Mondal");
		dcBean.setUserName("ujanmo");
		dcBean.setTransferAmt(100);
		dcBean.setCurrentBal(200);
		
		debitCardManagementImpl.addDebitCardCustomerData(dcBean);
		
		
        return "Rs. 100/- has been transferred from Ujan to " + toAccount;
    }

}
