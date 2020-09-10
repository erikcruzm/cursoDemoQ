package com.ipcom.demoQ.routes;

import javax.xml.bind.JAXBContext;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ipcom.demoQ.exception.DataException;
import com.ipcom.demoQ.model.Item;
import com.ipcom.demoQ.validate.ValidateDataProcessor;
import com.ipcom.demoQ.validate.processor.BuildSQLProcessor;


@Component
public class RouteAMQ extends RouteBuilder {

	
	@Autowired
	ValidateDataProcessor validateDataProcessor;
	
	@Autowired
	BuildSQLProcessor builSqlProcessor;

	@SuppressWarnings("deprecation")
	@Override
	public void configure() throws Exception {
		
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Item.class);
		xmlDataFormat.setContext(con);
		
		onException(DataException.class)
			.log(LoggingLevel.ERROR, "DataException en la ruta ${body}")
			.maximumRedeliveries(3)
			.redeliverDelay(300)
			.backOffMultiplier(2)
			.retryAttemptedLogLevel(LoggingLevel.ERROR);

		
		from("{{fromRoute}}")
			.log("Contenido del mensaje obtenido : ${body}")
			.unmarshal(xmlDataFormat)
			.process(validateDataProcessor)
			.process(builSqlProcessor)
			.log(" contenido body : ${body}")
			.log(" contenido header : ${in.headers.operacion}")
			.to("{{fromSqlQueue}}");
			
		
	}

}












