package com.hubciti.common.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.band.service.BandHelper;

public class BandSortByDate implements Comparator<String>{
    
    /**
    * Getting the Logger Instance.
    */
    private static final Logger LOG = LoggerFactory.getLogger(BandHelper.class);
    
    DateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    
    @Override
    public int compare(String key1, String key2) {
           
           try {
                  
                  return format.parse(key1).compareTo(format.parse(key2));
                  
           } catch (ParseException e) {
                  
                  LOG.info("Unable to Parse and sort : key1 : " + key1  + "Key2 : " +key2);
                  
                  return -1;
           }
    }
}
