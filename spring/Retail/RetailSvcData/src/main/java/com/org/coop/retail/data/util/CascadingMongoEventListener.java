package com.org.coop.retail.data.util;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.org.coop.retail.data.entities.CascadeSave;

@Service
public class CascadingMongoEventListener extends AbstractMongoEventListener {
  @Autowired
  private MongoOperations mongoOperations;

  @Override
  public void onBeforeConvert(final Object source) {
      ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {

          public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
              ReflectionUtils.makeAccessible(field);

              if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                  final Object fieldValue = field.get(source);

                  DbRefFieldCallback callback = new DbRefFieldCallback();

                  ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

                  if (!callback.isIdFound()) {
                      //throw new MappingException("Cannot perform cascade save on child object without id set");
                  }

                  mongoOperations.save(fieldValue);
              }
          }
      });
  }

  private static class DbRefFieldCallback implements ReflectionUtils.FieldCallback {
      private boolean idFound;

      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
          ReflectionUtils.makeAccessible(field);

          if (field.isAnnotationPresent(Id.class)) {
              idFound = true;
          }
      }

      public boolean isIdFound() {
          return idFound;
      }
  }
}
