package com.ipcom.demoQ.validate.processor;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.ipcom.demoQ.model.Item;


@Component
public class BuildSQLProcessor implements org.apache.camel.Processor {
	
    @Override
    public void process(Exchange exchange) throws Exception {
    	
    	Item item = (Item) exchange.getIn().getBody();
    	
    	StringBuilder query = new StringBuilder();
    	
    	if (item.getOperationType().equalsIgnoreCase("ADD")) {
			query.append("INSERT INTO ITEMS (SKU, ITEM_DESCRIPTION, PRICE) VALUES ('");
			query.append(item.getSku() + " ' , '" + item.getItemDescription() + "' , '" + item.getPrice() + "')");
		
    	} else if(item.getOperationType().equalsIgnoreCase("UPDATE")) {
    		
    		query.append("UPDATE ITEMS SET PRICE = ");
    		query.append(item.getPrice() + " WHERE SKU = '" + item.getSku() + "'");
    		
    	} else if(item.getOperationType().equalsIgnoreCase("DELETE")) {
    		
    		query.append("DELETE FROM ITEMS WHERE SKU = '" + item.getSku() +"'");
    	}
    	
    	exchange.getIn().setBody(query.toString());
    	exchange.getIn().setHeader("operacion", item.getOperationType());

    }
}