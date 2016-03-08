package com.org.coop.retail.data.util;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.org.coop.retail.data.entities.Timestamp;

@Service
public class TimestampMongoEventListener extends AbstractMongoEventListener {
  @Autowired
  private MongoOperations mongoOperations;

  @Override
  public void onBeforeConvert(final Object source) {
      ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {

          public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
              ReflectionUtils.makeAccessible(field);

              if (field.isAnnotationPresent(Timestamp.class)) {
                  Object fieldValue = field.get(source);
                  if(fieldValue == null) {
//                	  fieldValue = new java.sql.Timestamp(System.currentTimeMillis());
                	  fieldValue = new Date();
                  }

                  ReflectionUtils.setField(field, source, fieldValue);
              }
          }
      });
  }
}
