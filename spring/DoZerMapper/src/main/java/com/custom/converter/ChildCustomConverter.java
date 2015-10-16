package com.custom.converter;

import java.util.List;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;

import com.entity.Child;

public class ChildCustomConverter extends DozerConverter<Integer, List> implements MapperAware {

	public ChildCustomConverter() {
		super(Integer.class, List.class);
	}
	
	public ChildCustomConverter(Class<Integer> prototypeA, Class<List> prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer convertFrom(List src, Integer dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Child> convertTo(Integer src, List dest) {
		// TODO Auto-generated method stub
		if(src != null && dest != null && dest.size() > 0) {
				for(Object o : dest) {
					if(o instanceof Child) {
						Child c = (Child)o;
//						c.setfId(src);
						c.setmId(src);
					}
			}
		}
		return dest;
	}

}
