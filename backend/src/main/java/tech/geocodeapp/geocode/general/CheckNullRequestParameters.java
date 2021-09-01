package tech.geocodeapp.geocode.general;

import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;

import java.lang.reflect.Field;

public class CheckNullRequestParameters {
    public CheckNullRequestParameters(){

    }

    public void checkRequestParameters(Object request) throws NullRequestParameterException {
        /* check that all the fields of the request class are not NULL */
        for(Field field : request.getClass().getDeclaredFields()){
            field.setAccessible(true);

            try {
                if(field.get(request) == null) {
                    System.out.println(field.getName() + " was NULL");
                    throw new NullRequestParameterException();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
