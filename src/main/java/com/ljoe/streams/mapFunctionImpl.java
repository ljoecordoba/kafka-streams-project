package com.ljoe.streams;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mapFunctionImpl implements  KeyValueMapper<String, Log, KeyValue<? extends String, ? extends Log>>, Serializable {
    public static String customerPath = "/home/luciano/diccionarios/Customers.csv";
    public static String appliancesTypesPath = "/home/luciano/diccionarios/ApplianceType.csv";
    public static String marvinPath = "/home/luciano/diccionarios/Marvin.csv";
    public static List<String> appliancesTypes = new ArrayList<>(Arrays.asList(new String[]{"prod","dev","stage","uat","unknown"}));
    public static List<String> logChannelList = new ArrayList<>(Arrays.asList(new String[]{"black-idp-webconfig", "red-idp-audit", "red-idp-error", "red-idp-debug", "red-idp-authtrx"}));
    private Map<String,String> logChannelMap;
    Map<String,String> eventIDMap;


    public mapFunctionImpl(){

        logChannelMap = new HashMap<>();
        logChannelMap.put("SA_AUDIT","red-idp-audit");
        logChannelMap.put("SA_ERROR","red-idp-error");
        logChannelMap.put("SA_AUTHTRX","red-idp-authtrx");
        logChannelMap.put("SA_WEBCONFIG","rblack-idp-webconfig");
        logChannelMap.put("SA_DEBUG","red-idp-debug");

        eventIDMap = new HashMap<>();
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
    }

    @Override
    public KeyValue<String,Log> apply(String key, Log value) {
        UUID uuid = UUID.randomUUID();
        value.setDocumentId(uuid.toString());
        value.setApplianceid(value.getApplianceid().toLowerCase());

        Pattern p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        Matcher m = p.matcher(value.getApplianceid());
        value.setInvalidFieldApplianceID(m.matches());

        p = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");
        m = p.matcher(value.getCompanyid());
        value.setInvalidFieldCompanyID(m.matches());

        value.setNullFieldsApplianceID("".equals(value.getApplianceid()) || null == value.getApplianceid());
        value.setNullFIeldsCompanyID("".equals(value.getCompanyid()) || null == value.getCompanyid());
        value.setNullFieldsBrowserSession("".equals(value.getBrowsersession()) || null == value.getBrowsersession());
        value.setNullFieldsStateMachineId("".equals(value.getStatemachineid()) || null == value.getStatemachineid());

        Map<String,String> customersMap =  null;
        Map<String, String> appliancesTypesMap = null;
        Map<String,String> marvinMap = null;
        try {
            customersMap = parseCsvToDictionary(customerPath);
            appliancesTypesMap = parseCsvToDictionary(appliancesTypesPath);
            String companyID = customersMap.get(value.getApplianceid());
            String applianceType = appliancesTypesMap.get(value.getApplianceid());
            if(companyID != null){
                value.setCompanyIndex(companyID);
            }
            if(applianceType != null){
                value.setApplianceType(applianceType);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        value.setApplianceType(value.getApplianceType().toLowerCase());
        if(value.getApplianceType() == "" || value.getApplianceType() == null){
            value.setApplianceType("null");
        }


        if(!appliancesTypes.contains(value.getApplianceType())){
            value.setApplianceType("invalid");
            value.setInvalidApplianceType(true);
        }

        if("unknown".equals(value.getCompanyIndex())){
            value.setCompanyIndex("mismatch");
            value.setNoMarvinForYou(true);
        }

        if("SA_METRICBEAT".equals(value.getLogchannel())){
            value.setLogchannel("amber-metricbeat");
        }

        if(value.getMessage().contains("LogChannel")){
            value.setNoMarvinForYou(true);
            value.setLogchannel("black-malformed");
        }

//                  lowercase => ["[parsed][CompanyID]", "[parsed][ApplianceID]", "[parsed][RequestID]", "[parsed][BrowserSession]"]
        value.setCompanyIndex(value.getCompanyid().toLowerCase());
        value.setApplianceid(value.getApplianceid().toLowerCase());
        value.setRequestid(value.getRequestid().toLowerCase());
        value.setBrowsersession(value.getBrowsersession().toLowerCase());

        String logChannel = logChannelMap.get(value.getLogchannel());
        if( logChannel == null ){
            logChannel = "black-unknown";
        }
        value.setLogchannel(logChannel);


        Set<String> keys = eventIDMap.keySet();
        Iterator<String> ite = keys.iterator();

        while (ite.hasNext()) {
            String pattern = ite.next();
            Pattern r = Pattern.compile(pattern);
            Matcher m2 = r.matcher(value.getEventid());
            if (m2.find( )) {
                value.setEventType(ite.next());
            }
        }

        try {
            marvinMap = parseCsvToDictionary(marvinPath);
            String machineLearning = customersMap.get(value.getApplianceid());
            if(machineLearning != null){
                value.setMachineLearning(machineLearning);
            }
            else{
                value.setMachineLearning("false");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(value.getMachineLearning().equals(value.getCompanyid())){
            value.setMachineLearning("true");
        }
        else if ( !value.getMachineLearning().equals(value.getCompanyid()) && value.getMachineLearning().equals("false")){
            value.setMachineLearning("MisMatchCompanyID");
            value.setCompanyIndex("mismatch");
            value.setNoMarvinForYou(true);
        }

        value.setObfusscatedIP("NULL");

        return new KeyValue<>(key,value);
    }


    public static Map<String,String> parseCsvToDictionary(String path) throws ParseException, IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line =  null;
        HashMap<String,String> map = new HashMap<String, String>();

        while((line=br.readLine())!=null){
            String[] str = line.split(",");
            if(str.length > 1){
                map.put(str[0], str[1]);
            }
        }
        return map;
    }
}
