package net.yorksolutions.realestate.runListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.realestate.model.Customer;
import net.yorksolutions.realestate.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LoadCustomerDataListener implements ApplicationListener<ApplicationEvent> {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer[] customerArray = null;

        try {
            customerArray = objectMapper.readValue(getTestCustomerData(), Customer[].class);

            for (Customer customer : customerArray) {
                customerRepository.save(customer);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    String getTestCustomerData(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 2091,\n" +
                "        \"first\": \"Ho-Seng\",\n" +
                "        \"middle\": \"S\",\n" +
                "        \"last\": \"Thao\",\n" +
                "        \"street\": \"1484 Massa Ct\",\n" +
                "        \"city\": \"Collierville\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"zip\": 38027,\n" +
                "        \"email\": \"hosengthao@abc.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2092,\n" +
                "        \"first\": \"Bao\",\n" +
                "        \"middle\": \"M\",\n" +
                "        \"last\": \"Thao\",\n" +
                "        \"street\": \"123 ABC Ln\",\n" +
                "        \"city\": \"Somewhere\",\n" +
                "        \"state\": \"MN\",\n" +
                "        \"zip\": 55555,\n" +
                "        \"email\": \"baothao@abc.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2093,\n" +
                "        \"first\": \"Uriah\",\n" +
                "        \"middle\": \"M\",\n" +
                "        \"last\": \"Thao\",\n" +
                "        \"street\": \"123 ABC Ln\",\n" +
                "        \"city\": \"Somewhere\",\n" +
                "        \"state\": \"MN\",\n" +
                "        \"zip\": 55555,\n" +
                "        \"email\": \"uthao@abc.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2094,\n" +
                "        \"first\": \"Eliana\",\n" +
                "        \"middle\": \"C\",\n" +
                "        \"last\": \"Thao\",\n" +
                "        \"street\": \"123 ABC Ln\",\n" +
                "        \"city\": \"Somewhere\",\n" +
                "        \"state\": \"MN\",\n" +
                "        \"zip\": 55555,\n" +
                "        \"email\": \"ethao@abc.com\"\n" +
                "    }\n" +
                "]";
    }
}
