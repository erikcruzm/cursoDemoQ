package com.ipcom.demoQ.validate;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ipcom.demoQ.exception.DataException;
import com.ipcom.demoQ.model.Item;

@Component
public class ValidateDataProcessor implements Processor {
	
    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	Item item = (Item) exchange.getIn().getBody();
    	
    	if (ObjectUtils.isEmpty(item.getSku())) {
    		throw new DataException("Item sku no deberia ser nulo : " + item.getItemDescription());
		}

    }
}