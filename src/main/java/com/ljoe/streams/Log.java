package com.ljoe.streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Log implements Serializable
{
    private  String metadataBeat;
    private  String metadataType;
    private  String metadataVersion;
    private  String metadataTopic;
    //    Logger logger = LoggerFactory.getLogger(Log.class.getName());
    private String logchannel           ;
    private String formatversion        ;
    private String eventid              ;
    private String timestamp            ;
    private String companyid            ;
    private String applianceid          ;
    private String appliancemachinename ;
    private String realm                ;
    private String userid               ;
    private String hasheduserid         ;
    private String useragent            ;
    private String userhostaddress      ;
    private String producttype          ;
    private String receivetoken         ;
    private String usejava              ;
    private String allowedtoken         ;
    private String authguimode          ;
    private String authregmethod        ;
    private String authregmethodinfo    ;
    private String ispreauth            ;
    private String preauthpage          ;
    private String destinationsiteurl   ;
    private String returnurl            ;
    private String targeturl            ;
    private String samlconsumersiteurl  ;
    private String samlrelaystate       ;
    private String samltargeturl        ;
    private String succeed              ;
    private String comment              ;
    private String analyzeengineresult  ;
    private String browsersession       ;
    private String statemachineid       ;
    private String requestid            ;
    private String message              ;



   //fields represents a flag
    private boolean invalidFieldAppliance = false;
    private boolean invalidFieldCompanyID = false;
    private boolean nullFieldsApplianceID = false;
    private boolean nullFIeldsCompanyID = false;
    private boolean nullFieldsBrowserSession = false;
    private boolean nullFieldsStateMachineId = false;
    private boolean invalidApplianceType = false;
    private boolean noMarvinForYou = false;



    //fields initialized by default
    private String documentId = "";
    private String logchannelIndex = "black-malformed";
    private String companyIndex = "unknown";
    private String applianceType = "unknown";
    private String eventType = "NoMatch";

    private String machineLearning = "";
    private String obfusscatedIP = "";

   // public Log(String rawMessage) {
   //     List<String>  matchList = Arrays.asList(rawMessage.split("\""));
   //     System.out.println("El tamaño de la lista es: " + matchList.size());
   //     try{
   //         logchannel            = matchList.get(1).replace("\\","");
   //         formatversion         = matchList.get(3).replace("\\","");
   //         eventid               = matchList.get(5).replace("\\","");
   //         timestamp             = matchList.get(7).replace("\\","");
   //         companyid             = matchList.get(9).replace("\\","");
   //         applianceid           = matchList.get(11).replace("\\","");
   //         appliancemachinename  = matchList.get(13).replace("\\","");
   //         realm                 = matchList.get(15).replace("\\","");
   //         userid                = matchList.get(17).replace("\\","");
   //         hasheduserid          = matchList.get(19).replace("\\","");
   //         useragent             = matchList.get(21).replace("\\","");
   //         userhostaddress       = matchList.get(23).replace("\\","");
   //         producttype           = matchList.get(25).replace("\\","");
   //         receivetoken          = matchList.get(27).replace("\\","");
   //         usejava               = matchList.get(29).replace("\\","");
   //         allowedtoken          = matchList.get(31).replace("\\","");
   //         authguimode           = matchList.get(33).replace("\\","");
   //         authregmethod         = matchList.get(35).replace("\\","");
   //         authregmethodinfo     = matchList.get(37).replace("\\","");
   //         ispreauth             = matchList.get(39).replace("\\","");
   //         preauthpage           = matchList.get(41).replace("\\","");
   //         destinationsiteurl    = matchList.get(43).replace("\\","");
   //         returnurl             = matchList.get(45).replace("\\","");
   //         targeturl             = matchList.get(47).replace("\\","");
   //         samlconsumersiteurl   = matchList.get(49).replace("\\","");
   //         samlrelaystate        = matchList.get(51).replace("\\","");
   //         samltargeturl         = matchList.get(53).replace("\\","");
   //         succeed               = matchList.get(55).replace("\\","");
   //         comment               = matchList.get(57).replace("\\","");
   //         analyzeengineresult   = matchList.get(59).replace("\\","");
   //         browsersession        = matchList.get(61).replace("\\","");
   //         statemachineid        = matchList.get(63).replace("\\","");
   //         requestid             = matchList.get(65).replace("\\","");
   //         message               = matchList.get(67).replace("\\","");
   //     }
   //     catch (IndexOutOfBoundsException e){
   //         System.out.println("An error has occurred at filling the fields of the log");
   //     }
//
   // }
    public Log(String rawMessage){
        JSONObject obj = new JSONObject(rawMessage);
        timestamp = obj.getString("@timestamp");
        metadataBeat = obj.getJSONObject("@metadata").getString("beat");
        metadataType = obj.getJSONObject("@metadata").getString("type");
        metadataVersion = obj.getJSONObject("@metadata").getString("version");
        metadataTopic = obj.getJSONObject("@metadata").getString("topic");

        initializeFields(obj.getString("message"));


    }

    public void initializeFields(String message){
            List<String>  matchList = Arrays.asList(message.split("\""));
            System.out.println("El tamaño de la lista es: " + matchList.size());
            try{
                logchannel            = matchList.get(1).replace("\\","");
                formatversion         = matchList.get(3).replace("\\","");
                eventid               = matchList.get(5).replace("\\","");
                timestamp             = matchList.get(7).replace("\\","");
                companyid             = matchList.get(9).replace("\\","");
                applianceid           = matchList.get(11).replace("\\","");
                appliancemachinename  = matchList.get(13).replace("\\","");
                realm                 = matchList.get(15).replace("\\","");
                userid                = matchList.get(17).replace("\\","");
                hasheduserid          = matchList.get(19).replace("\\","");
                useragent             = matchList.get(21).replace("\\","");
                userhostaddress       = matchList.get(23).replace("\\","");
                producttype           = matchList.get(25).replace("\\","");
                receivetoken          = matchList.get(27).replace("\\","");
                usejava               = matchList.get(29).replace("\\","");
                allowedtoken          = matchList.get(31).replace("\\","");
                authguimode           = matchList.get(33).replace("\\","");
                authregmethod         = matchList.get(35).replace("\\","");
                authregmethodinfo     = matchList.get(37).replace("\\","");
                ispreauth             = matchList.get(39).replace("\\","");
                preauthpage           = matchList.get(41).replace("\\","");
                destinationsiteurl    = matchList.get(43).replace("\\","");
                returnurl             = matchList.get(45).replace("\\","");
                targeturl             = matchList.get(47).replace("\\","");
                samlconsumersiteurl   = matchList.get(49).replace("\\","");
                samlrelaystate        = matchList.get(51).replace("\\","");
                samltargeturl         = matchList.get(53).replace("\\","");
                succeed               = matchList.get(55).replace("\\","");
                comment               = matchList.get(57).replace("\\","");
                analyzeengineresult   = matchList.get(59).replace("\\","");
                browsersession        = matchList.get(61).replace("\\","");
                statemachineid        = matchList.get(63).replace("\\","");
                requestid             = matchList.get(65).replace("\\","");
                this.message               = matchList.get(67).replace("\\","");
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("An error has occurred at filling the fields of the log");
            }
    }

    public String getLogchannel() {
        return logchannel;
    }

    public void setLogchannel(String logchannel) {
        this.logchannel = logchannel;
    }

    public String getFormatversion() {
        return formatversion;
    }

    public void setFormatversion(String formatversion) {
        this.formatversion = formatversion;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getApplianceid() {
        return applianceid;
    }

    public void setApplianceid(String applianceid) {
        this.applianceid = applianceid;
    }

    public String getAppliancemachinename() {
        return appliancemachinename;
    }

    public void setAppliancemachinename(String appliancemachinename) {
        this.appliancemachinename = appliancemachinename;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHasheduserid() {
        return hasheduserid;
    }

    public void setHasheduserid(String hasheduserid) {
        this.hasheduserid = hasheduserid;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getUserhostaddress() {
        return userhostaddress;
    }

    public void setUserhostaddress(String userhostaddress) {
        this.userhostaddress = userhostaddress;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getReceivetoken() {
        return receivetoken;
    }

    public void setReceivetoken(String receivetoken) {
        this.receivetoken = receivetoken;
    }

    public String getUsejava() {
        return usejava;
    }

    public void setUsejava(String usejava) {
        this.usejava = usejava;
    }

    public String getAllowedtoken() {
        return allowedtoken;
    }

    public void setAllowedtoken(String allowedtoken) {
        this.allowedtoken = allowedtoken;
    }

    public String getAuthguimode() {
        return authguimode;
    }

    public void setAuthguimode(String authguimode) {
        this.authguimode = authguimode;
    }

    public String getAuthregmethod() {
        return authregmethod;
    }

    public void setAuthregmethod(String authregmethod) {
        this.authregmethod = authregmethod;
    }

    public String getAuthregmethodinfo() {
        return authregmethodinfo;
    }

    public void setAuthregmethodinfo(String authregmethodinfo) {
        this.authregmethodinfo = authregmethodinfo;
    }

    public String getIspreauth() {
        return ispreauth;
    }

    public void setIspreauth(String ispreauth) {
        this.ispreauth = ispreauth;
    }

    public String getPreauthpage() {
        return preauthpage;
    }

    public void setPreauthpage(String preauthpage) {
        this.preauthpage = preauthpage;
    }

    public String getDestinationsiteurl() {
        return destinationsiteurl;
    }

    public void setDestinationsiteurl(String destinationsiteurl) {
        this.destinationsiteurl = destinationsiteurl;
    }

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    public String getSamlconsumersiteurl() {
        return samlconsumersiteurl;
    }

    public void setSamlconsumersiteurl(String samlconsumersiteurl) {
        this.samlconsumersiteurl = samlconsumersiteurl;
    }

    public String getSamlrelaystate() {
        return samlrelaystate;
    }

    public void setSamlrelaystate(String samlrelaystate) {
        this.samlrelaystate = samlrelaystate;
    }

    public String getSamltargeturl() {
        return samltargeturl;
    }

    public void setSamltargeturl(String samltargeturl) {
        this.samltargeturl = samltargeturl;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAnalyzeengineresult() {
        return analyzeengineresult;
    }

    public void setAnalyzeengineresult(String analyzeengineresult) {
        this.analyzeengineresult = analyzeengineresult;
    }

    public String getBrowsersession() {
        return browsersession;
    }

    public void setBrowsersession(String browsersession) {
        this.browsersession = browsersession;
    }

    public String getStatemachineid() {
        return statemachineid;
    }

    public void setStatemachineid(String statemachineid) {
        this.statemachineid = statemachineid;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setLogchannelIndex(String s) {
        this.logchannelIndex = s;
    }

    public void setDocumentId(String s) {
        this.documentId = s;
    }

    public String getMetadataBeat() {
        return metadataBeat;
    }

    public void setMetadataBeat(String metadataBeat) {
        this.metadataBeat = metadataBeat;
    }

    public String getMetadataType() {
        return metadataType;
    }

    public void setMetadataType(String metadataType) {
        this.metadataType = metadataType;
    }

    public String getMetadataVersion() {
        return metadataVersion;
    }

    public void setMetadataVersion(String metadataVersion) {
        this.metadataVersion = metadataVersion;
    }

    public String getMetadataTopic() {
        return metadataTopic;
    }

    public void setMetadataTopic(String metadataTopic) {
        this.metadataTopic = metadataTopic;
    }

    public boolean isInvalidFieldApplianceID() {
        return invalidFieldAppliance;
    }

    public void setInvalidFieldApplianceID(boolean invalidFieldApplianceID) {
        this.invalidFieldAppliance = invalidFieldAppliance;
    }

    public boolean isInvalidFieldCompanyID() {
        return invalidFieldCompanyID;
    }

    public void setInvalidFieldCompanyID(boolean invalidFieldCompanyID) {
        this.invalidFieldCompanyID = invalidFieldCompanyID;
    }

    public boolean isNullFieldsApplianceID() {
        return nullFieldsApplianceID;
    }

    public void setNullFieldsApplianceID(boolean nullFieldsApplianceID) {
        this.nullFieldsApplianceID = nullFieldsApplianceID;
    }

    public boolean isNullFIeldsCompanyID() {
        return nullFIeldsCompanyID;
    }

    public void setNullFIeldsCompanyID(boolean nullFIeldsCompanyID) {
        this.nullFIeldsCompanyID = nullFIeldsCompanyID;
    }

    public boolean isNullFieldsBrowserSession() {
        return nullFieldsBrowserSession;
    }

    public void setNullFieldsBrowserSession(boolean nullFieldsBrowserSession) {
        this.nullFieldsBrowserSession = nullFieldsBrowserSession;
    }

    public boolean isNullFieldsStateMachineId() {
        return nullFieldsStateMachineId;
    }

    public void setNullFieldsStateMachineId(boolean nullFieldsStateMachineId) {
        this.nullFieldsStateMachineId = nullFieldsStateMachineId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getLogchannelIndex() {
        return logchannelIndex;
    }

    public String getCompanyIndex() {
        return companyIndex;
    }

    public void setCompanyIndex(String companyIndex) {
        this.companyIndex = companyIndex;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    public boolean isInvalidFieldAppliance() {
        return invalidFieldAppliance;
    }

    public void setInvalidFieldAppliance(boolean invalidFieldAppliance) {
        this.invalidFieldAppliance = invalidFieldAppliance;
    }

    public boolean isInvalidApplianceType() {
        return invalidApplianceType;
    }

    public void setInvalidApplianceType(boolean invalidApplianceType) {
        this.invalidApplianceType = invalidApplianceType;
    }


    public boolean isNoMarvinForYou() {
        return noMarvinForYou;
    }

    public void setNoMarvinForYou(boolean noMarvinForYou) {
        this.noMarvinForYou = noMarvinForYou;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMachineLearning() {
        return machineLearning;
    }

    public void setMachineLearning(String machineLearning) {
        this.machineLearning = machineLearning;
    }

    public String getObfusscatedIP() {
        return obfusscatedIP;
    }

    public void setObfusscatedIP(String obfusscatedIP) {
        this.obfusscatedIP = obfusscatedIP;
    }


    @Override
    public String toString() {
        return "Log{" +
                "metadataBeat='" + metadataBeat + '\'' +
                ", metadataType='" + metadataType + '\'' +
                ", metadataVersion='" + metadataVersion + '\'' +
                ", metadataTopic='" + metadataTopic + '\'' +
                ", logchannel='" + logchannel + '\'' +
                ", formatversion='" + formatversion + '\'' +
                ", eventid='" + eventid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", companyid='" + companyid + '\'' +
                ", applianceid='" + applianceid + '\'' +
                ", appliancemachinename='" + appliancemachinename + '\'' +
                ", realm='" + realm + '\'' +
                ", userid='" + userid + '\'' +
                ", hasheduserid='" + hasheduserid + '\'' +
                ", useragent='" + useragent + '\'' +
                ", userhostaddress='" + userhostaddress + '\'' +
                ", producttype='" + producttype + '\'' +
                ", receivetoken='" + receivetoken + '\'' +
                ", usejava='" + usejava + '\'' +
                ", allowedtoken='" + allowedtoken + '\'' +
                ", authguimode='" + authguimode + '\'' +
                ", authregmethod='" + authregmethod + '\'' +
                ", authregmethodinfo='" + authregmethodinfo + '\'' +
                ", ispreauth='" + ispreauth + '\'' +
                ", preauthpage='" + preauthpage + '\'' +
                ", destinationsiteurl='" + destinationsiteurl + '\'' +
                ", returnurl='" + returnurl + '\'' +
                ", targeturl='" + targeturl + '\'' +
                ", samlconsumersiteurl='" + samlconsumersiteurl + '\'' +
                ", samlrelaystate='" + samlrelaystate + '\'' +
                ", samltargeturl='" + samltargeturl + '\'' +
                ", succeed='" + succeed + '\'' +
                ", comment='" + comment + '\'' +
                ", analyzeengineresult='" + analyzeengineresult + '\'' +
                ", browsersession='" + browsersession + '\'' +
                ", statemachineid='" + statemachineid + '\'' +
                ", requestid='" + requestid + '\'' +
                ", message='" + message + '\'' +
                ", invalidFieldAppliance=" + invalidFieldAppliance +
                ", invalidFieldCompanyID=" + invalidFieldCompanyID +
                ", nullFieldsApplianceID=" + nullFieldsApplianceID +
                ", nullFIeldsCompanyID=" + nullFIeldsCompanyID +
                ", nullFieldsBrowserSession=" + nullFieldsBrowserSession +
                ", nullFieldsStateMachineId=" + nullFieldsStateMachineId +
                ", invalidApplianceType=" + invalidApplianceType +
                ", noMarvinForYou=" + noMarvinForYou +
                ", documentId='" + documentId + '\'' +
                ", logchannelIndex='" + logchannelIndex + '\'' +
                ", companyIndex='" + companyIndex + '\'' +
                ", applianceType='" + applianceType + '\'' +
                ", eventType='" + eventType + '\'' +
                ", machineLearning='" + machineLearning + '\'' +
                ", obfusscatedIP='" + obfusscatedIP + '\'' +
                '}';
    }
}
