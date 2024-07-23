package marketplace;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
///**
// *
// * @author Martin Pilar mpilarcastillejo@gmail.com
// */
//public class DynamoDBConfig {
//
//    @Value("${amazonProperties.serviceEndPoint}")
//    private String serviceEndPoint;
//
//    @Value("${amazonProperties.region}")
//    private String region;
//
//    @Value("${amazonProperties.accessKey}")
//    private String amazonAWSAccessKey;
//
//    @Value("${amazonProperties.secretKey}")
//    private String amazonAWSSecretKey;
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        return AmazonDynamoDBClientBuilder
//                .standard()
//                .withEndpointConfiguration(
//                        new AwsClientBuilder.EndpointConfiguration(
//                                serviceEndPoint,
//                                region
//                        )
//                ).withCredentials(
//                        new AWSStaticCredentialsProvider(
//                                amazonAWSCredentials()
//                        )
//                ).build();
//    }
//
//    @Bean
//    public AWSCredentials amazonAWSCredentials() {
//        return new BasicAWSCredentials(
//                amazonAWSAccessKey, amazonAWSSecretKey);
//    }
//}
