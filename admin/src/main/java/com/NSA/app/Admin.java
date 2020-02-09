package com.NSA.app;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;

public class Admin 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        createTopic("test_admin", 1);
        deleteTopic("test_admin");
    }
    public static void createTopic(final String topicName, final int partitions) {
        final short replicationFactor = 1;

        // Create admin client
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        try (final AdminClient adminClient = KafkaAdminClient.create(props)) {
            try {
                // Define topic
                final NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

                // Create topic, which is async call.
                final CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));

                // Since the call is Async, Lets wait for it to complete.
                createTopicsResult.values().get(topicName).get();
            } catch (InterruptedException | ExecutionException e) {
                if (!(e.getCause() instanceof TopicExistsException)) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                // TopicExistsException - Swallow this exception, just means the topic already exists.
            }
            System.out.println("Created topic " + topicName);
        }
    }
    
    public static void deleteTopic(final String topicName) {

        // Create admin client
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        try (final AdminClient adminClient = KafkaAdminClient.create(props)) {
            try {
                // Define topic
             

                // Create topic, which is async call.
                final DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singleton(topicName));

                // Since the call is Async, Lets wait for it to complete.
                deleteTopicsResult.values().get(topicName).get();
            } catch (InterruptedException | ExecutionException e) {
                if (!(e.getCause() instanceof TopicExistsException)) {
                    throw new RuntimeException(e.getMessage(), e);
                }
                // TopicExistsException - Swallow this exception, just means the topic already exists.
            }
            System.out.println("Deleted topic " + topicName);
        }
    }
}
