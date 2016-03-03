package com.org.coop.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.AddressBean;
import com.org.coop.society.data.admin.entities.Address;
import com.org.coop.society.data.admin.entities.DistrictMaster;
import com.org.coop.society.data.admin.repositories.DistrictMasterRepository;

@Component("addressCC")
public class AddressCC extends DozerConverter<Integer, DistrictMaster> implements MapperAware, CustomConverter {
	@Autowired
	private DistrictMasterRepository districtMasterRepository;
	
	public AddressCC() {
		super(Integer.class, DistrictMaster.class);
	}
	
	public AddressCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer convertFrom(DistrictMaster src, Integer dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DistrictMaster convertTo(Integer src, DistrictMaster dest) {
		// TODO Auto-generated method stub
		if(src != null) {
			dest = districtMasterRepository.findByDistId(src);
		}
		return dest;
	}
}
