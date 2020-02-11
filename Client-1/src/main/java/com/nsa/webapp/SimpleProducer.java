package com.nsa.webapp;

//import util.properties packages
import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleProducer {
	    
	    public void inputToProducer(String producerInput,String topicName) {
	    	 //topicName = producerInput;
		    System.out.println("Received topic:" + topicName);
		    
		    // create instance for properties to access producer configs   
		    Properties props = new Properties();
		    
		    //Assign localhost id
		    props.put("bootstrap.servers", "localhost:9092");
		    
		    //Set acknowledgements for producer requests.      
		    props.put("acks", "all");
		    
		    //If the request fails, the producer can automatically retry,
		    props.put("retries", 0);
		    
		    //Specify buffer size in config
		    props.put("batch.size", 16384);
		    
		    //Reduce the no of requests less than 0   
		    props.put("linger.ms", 1);
		    
		    //The buffer.memory controls the total amount of memory available to the producer for buffering.   
		    props.put("buffer.memory", 33554432);
		    
		    props.put("key.serializer", 
		    		StringSerializer.class.getName());
		       
		    props.put("value.serializer", 
		    		StringSerializer.class.getName());
		    
//		    Producer<String, String> producer = new KafkaProducer<String, String>();
		    Producer<String, String> producer = new KafkaProducer
		       <String, String>(props);
		          
		 
		       producer.send(new ProducerRecord<String, String>(topicName, 
		          Integer.toString(1), producerInput));
		             System.out.println("Message sent successfully");
		             producer.close();
		 }
	    }
	    //Assign topicName to string variable
	    