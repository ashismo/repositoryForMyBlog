package com.org.coop.retail.servicehelper;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.data.entities.Sequence;
import com.org.coop.retail.data.repositories.SequenceRepository;

@Service
public class RetailUtilServiceHelperImpl {
	@Autowired private MongoOperations mongo;
	   
	@Autowired
	private SequenceRepository sequenceRepository;
	
	  public RetailData getNextSequence(String collectionName) {
		  Sequence sequence = mongo.findAndModify(
		      query(where("_id").is(collectionName)), 
		      new Update().inc("seq", 1),
		      options().returnNew(true),
		      Sequence.class);
		  
		  if(sequence == null) {
			  sequence = new Sequence();
			  sequence.setId(collectionName);
			  sequence.setSeq(1);
			  sequenceRepository.save(sequence);
		  }
		  RetailData retailData = new RetailData();
		  retailData.setSequence(sequence);
		  return retailData;
	  }
}
