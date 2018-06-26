package co.com.bancolombia.service.resolveEnigma.Strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class JoinReplyAggregationStrategy implements AggregationStrategy {

    public Exchange aggregate(Exchange exchange1, Exchange exchange2) {
        if (exchange1 == null) {
            return exchange2;
        } else {
            String code1 = (String) exchange1.getProperty("Error");
            String code2 = (String) exchange2.getProperty("Error");
            String desc1 = (String) exchange1.getProperty("descError");
            String desc2 = (String) exchange2.getProperty("descError");
            
            if (code1.equalsIgnoreCase("0000") && code2.equalsIgnoreCase("0000")) 
            {
            	String step1 = (String) exchange2.getProperty("Step1");
            	String step2 = (String) exchange2.getProperty("Step2");
            	String step3 = (String) exchange2.getProperty("Step3");
            	
            	if (step1 != null) 
            	{
            		exchange1.setProperty("Step1", step1);
            	}else if (step2!= null) 
            	{
            		exchange1.setProperty("Step2", step2);
            	}else if (step3 != null) 
            	{
            		exchange1.setProperty("Step3", step3);
            	}
            	
            	
            }
            else if (!code2.equalsIgnoreCase("0000") )
            {
            	exchange1.setProperty("Error", code2);
               	exchange1.setProperty("descError", desc2);
            }
            return exchange1;
        }
    }

}
