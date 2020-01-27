package com.ljoe.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Processor {

    KafkaStreams kafkaStreams;
    public static InetAddress inetAddress;
    public static Logger logger = LoggerFactory.getLogger(Main.class.getName());

    StreamsBuilder streamsBuilder;
    Properties properties;

    public Processor(){
        properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG,"demo-kafka-streams");
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.StringSerde.class.getName());


        Map<String,String> logChannelMap = new HashMap<>();
        logChannelMap.put("SA_AUDIT","red-idp-audit");
        logChannelMap.put("SA_ERROR","red-idp-error");
        logChannelMap.put("SA_AUTHTRX","red-idp-authtrx");
        logChannelMap.put("SA_WEBCONFIG","rblack-idp-webconfig");
        logChannelMap.put("SA_DEBUG","red-idp-debug");

        Map<String,String> eventIDMap = new HashMap<>();
        eventIDMap.put("^11\\d{3}$","LogAdminEnum.Workflow");
        eventIDMap.put("^111\\d{2}$", "LogAdminEnum.Api");
        eventIDMap.put( "^12\\d{3}$", "LogAdminEnum.Data");
        eventIDMap.put(      "^13\\d{3}$", "LogAdminEnum.Regcode");
        eventIDMap.put(     "^14\\d{3}$", "LogAdminEnum.Ipsec");
        eventIDMap.put(     "^15\\d{3}$", "LogAdminEnum.Access");
        eventIDMap.put(       "^16\\d{3}$", "LogAdminEnum.Password");
        eventIDMap.put(      "^17\\d{3}$", "LogAdminEnum.License");
        eventIDMap.put(     "^18\\d{3}$", "LogAdminEnum.Postauth");
        eventIDMap.put(   "^19\\d{3}$", "LogAdminEnum.Other");
        eventIDMap.put( "^1\\d{4}$", "LogEnum.AdminUI");
        eventIDMap.put(      "^2\\d{4}$", "LogEnum.State");
        eventIDMap.put(  "^3\\d{4}$", "LogEnum.PreAuth");
        eventIDMap.put(    "^4\\d{4}$", "LogEnum.PostAuth");
        eventIDMap.put(   "^5\\d{4}$", "LogEnum.Provider");
        eventIDMap.put(  "^51\\d{3}$", "LogEnum.MemberShipProvider");
        eventIDMap.put(  "^52\\d{3}$", "LogEnum.ProfileProvider");
        eventIDMap.put(  "^53\\d{3}$", "LogEnum.OTPProvider");
        eventIDMap.put( "^54\\d{3}$", "LogEnum.CertificateProvider");
        eventIDMap.put( "^55\\d{3}$", "LogEnum.LTAProvider");
        eventIDMap.put( "^6\\d{4}$", "LogEnum.Api");
        eventIDMap.put("^9\\d{4}$", "LogEnum.System");
        eventIDMap.put("^92\\d{3}$", "LogEnum.AnalyzeEngine");
        eventIDMap.put("^93\\d{3}$", "LogEnum.BehaveBio");
        eventIDMap.put("^2\\d{4}$", "LogUserEnum.Login");
        eventIDMap.put("^4\\d{4}$", "LogPostAuthEnum.ID");
        streamsBuilder = new StreamsBuilder();



        KStream<String,String> inputTopic = streamsBuilder.stream("kafka_streams");


//       KStream<String,Log> logsStream = inputTopic.map(
//               (k,v) -> {
//                   Log log = new Log(v);
//                   return new KeyValue<>(k,log);
//               }
//       );

//       KStream<String,Log> logKStream = logsStream.map(new mapFunctionImpl());

//       KStream<String,String> kstreamString = logKStream.map((key, value) -> {
//           return new KeyValue<>(key,value.toString());
//        });

    //  KStream<String,Log> ks2 = logKStream.filter((key, value) ->
    //  {
    //      return value.getMachineLearning().equals("true") && !value.isNoMarvinForYou();
    //  }
    //  );

    //  KStream<String,Log> ks3 = logKStream.filter((key, value) -> {
    //      return value.getMachineLearning().equals("true") && !value.isNoMarvinForYou() && value.getApplianceType().contains("prod");
    //  });


    //  KStream<String, Log> ks4 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("prod");
    //  });

    //  KStream<String,Log> ks5 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("dev");
    //  });

    //  KStream<String,Log> ks6 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("stage");
    //  });

    //  KStream<String,Log> ks7 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("uat");
    //  });

    //  KStream<String,Log> ks8 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("unknown");
    //  });

    //  KStream<String,Log> ks9 = logKStream.filter((key, value) -> {
    //      return logChannelList.contains(value.getLogchannelIndex()) && value.getApplianceType().contains("invalid");
    //  });

      // ks2.to("idp_amber_topic");

      // ks3.to("ml_trx_risk_scoring_topic");

      // ks4.to("idp_production_topic");

      // ks5.to("idp_non_production_topic");
      // ks6.to("idp_non_production_topic");
      // ks7.to("idp_non_production_topic");
      // ks8.to("idp_non_production_topic");
      // ks9.to("idp_non_production_topic");

        inputTopic.to("kafka_streams");



    }
    public void run(){
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(),properties);
        kafkaStreams.start();
    }




//    public static void main(String[] args) throws IOException, ParseException {
//        Map<String,String> map = parseCsvToDictionary(appliancesTypesPath);
//    }
}
