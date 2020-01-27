package com.ljoe.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{

    public static Logger logger = LoggerFactory.getLogger(Main.class.getName());
    public static String customerPath = "/home/luciano/diccionarios/Customers.csv";
    public static String appliancesTypesPath = "/home/luciano/diccionarios/ApplianceType.csv";
    public static List<String> appliancesTypes = new ArrayList<>(Arrays.asList(new String[]{"prod","dev","stage","uat","unknown"}));

    public static void main(String[] args)  {
        // create properties


//  Properties properties = new Properties();
//  properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
//  properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,"demo-kafka-streams");
//  properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
//  properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.StringSerde.class.getName());
//  //create a topology
// Map<String,String> logChannelMap = new HashMap<>();
// logChannelMap.put("SA_AUDIT","red-idp-audit");
//  logChannelMap.put("SA_ERROR","red-idp-error");
//  logChannelMap.put("SA_AUTHTRX","red-idp-authtrx");
//  logChannelMap.put("SA_WEBCONFIG","rblack-idp-webconfig");
//  logChannelMap.put("SA_DEBUG","red-idp-debug");


//  StreamsBuilder streamsBuilder = new StreamsBuilder();
//  //input topic
//  KStream<String,String> inputTopic = streamsBuilder.stream("kafka_streams");

// KStream<String,Log> logsStream = inputTopic.map(
//         (k,v) -> {
//             Log log = new Log(v);
//             return new KeyValue<>(k,log);
//         }
// );

// KStream<String,Log> logKStream = logsStream.map((key, value) -> {
//     UUID uuid = UUID.randomUUID();
//     value.setDocumentId(uuid.toString());
//     value.setApplianceid(value.getApplianceid().toLowerCase());

//     Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
//     Matcher m = p.matcher(value.getApplianceid());
//     value.setInvalidFieldApplianceID(m.matches());

//     p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
//     m = p.matcher(value.getCompanyid());
//     value.setInvalidFieldCompanyID(m.matches());

//     value.setNullFieldsApplianceID("".equals(value.getApplianceid()) || null == value.getApplianceid());
//     value.setNullFIeldsCompanyID("".equals(value.getCompanyid()) || null == value.getCompanyid());
//     value.setNullFieldsBrowserSession("".equals(value.getBrowsersession()) || null == value.getBrowsersession());
//     value.setNullFieldsStateMachineId("".equals(value.getStatemachineid()) || null == value.getStatemachineid());

//     Map<String,String> customersMap =  null;
//     Map<String, String> appliancesTypesMap = null;
//     try {
//         customersMap = parseCsvToDictionary(customerPath);
//         appliancesTypesMap = parseCsvToDictionary(appliancesTypesPath);
//         String companyID = customersMap.get(value.getApplianceid());
//         String applianceType = appliancesTypesMap.get(value.getApplianceid());
//         if(companyID != null){
//             value.setCompanyIndex(companyID);
//         }
//         if(applianceType != null){
//             value.setApplianceType(applianceType);
//         }

//     } catch (ParseException e) {
//         e.printStackTrace();
//     } catch (IOException e) {
//         e.printStackTrace();
//     }
//     value.setApplianceType(value.getApplianceType().toLowerCase());
//     if(value.getApplianceType() == "" || value.getApplianceType() == null){
//          value.setApplianceType("null");
//     }


//     if(!appliancesTypes.contains(value.getApplianceType())){
//          value.setApplianceType("invalid");
//          value.setInvalidApplianceType(true);
//     }



//     return new KeyValue<>(key,value);
// });



//  logsStream.to("kafka_streams_2");

//  //build the topology

//  KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(),properties);

//  //start our streams application

//  kafkaStreams.start();
        Processor processor = new Processor();
        processor.run();
    }







}
