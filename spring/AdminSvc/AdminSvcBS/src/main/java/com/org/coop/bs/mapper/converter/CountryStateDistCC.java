package com.org.coop.bs.mapper.converter;

import org.apache.log4j.Logger;
import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.admin.service.MasterDataSetupServiceImpl;
import com.org.coop.canonical.master.beans.DistrictMasterBean;
import com.org.coop.canonical.master.beans.StateMasterBean;
import com.org.coop.society.data.admin.entities.CountryMaster;
import com.org.coop.society.data.admin.entities.StateMaster;
import com.org.coop.society.data.admin.repositories.CountryMasterRepository;
import com.org.coop.society.data.admin.repositories.StateMasterRepository;

@Component("countryStateDistCC")
public class CountryStateDistCC extends DozerConverter<Object, Object> implements MapperAware, CustomConverter {
	private static final Logger log = Logger.getLogger(CountryStateDistCC.class); 
	
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	
	@Autowired
	private StateMasterRepository stateMasterRepository;
	
	public CountryStateDistCC() {
		super(Object.class, Object.class);
	}
	
	public CountryStateDistCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object convertFrom(Object src, Object dest) {
		if(log.isDebugEnabled()) {
			log.debug("Passed parameter from the mapping xml file: " + getParameter());
		}
		if("stateToCountryCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from state bean
			StateMasterBean state = (StateMasterBean)src;
			CountryMaster country = countryMasterRepository.findByCountryCode(state.getCountryCode());
			dest = country;
		} else if("districtToCountryCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from district bean
			DistrictMasterBean district = (DistrictMasterBean)src;
			CountryMaster country = countryMasterRepository.findByCountryCode(district.getCountryCode());
			dest = country;
		} else if("districtToStateCopy".equalsIgnoreCase(getParameter())) {
			// Populate country from district bean
			DistrictMasterBean district = (DistrictMasterBean)src;
			StateMaster state = stateMasterRepository.findByStateCodeAndCountryCode(district.getStateCode(), district.getCountryCode());
			dest = state;
		}
		return dest;
	}

	@Override
	public Object convertTo(Object src, Object dest) {
		System.out.println("----" + getParameter());
		return dest;
	}
}
