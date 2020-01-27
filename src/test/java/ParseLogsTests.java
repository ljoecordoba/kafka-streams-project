import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class ParseLogsTests {

    public static String testString = "LogChannel=\\\"SA_AUTHTRX\\\" FormatVersion=\\\"0.0.1\\\" EventID=\\\"20990\\\" Timestamp=\\\"2019-02-07T16:18:44.094Z\\\" CompanyID=\\\"3041ac47-6e50-458f-8a53-a9553795e683\\\" ApplianceID=\\\"33f0177f-ce65-e811-90bc-00505691306b\\\" ApplianceMachineName=\\\"SNA0QAPORTAL22\\\" Realm=\\\"SecureAuth0\\\" UserID=\\\"mtest1\\\" HashedUserID=\\\"o8Qy2x327qtUZZ7d1RieJRiNjIU=\\\" UserAgent=\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36\\\" UserHostAddress=\\\"172.21.103.141\\\" ProductType=\\\"1\\\" ReceiveToken=\\\"4\\\" UseJava=\\\"False\\\" AllowedToken=\\\"BROWSERFINGERPRINT\\\" AuthGuiMode=\\\"1\\\"  AuthRegMethod=\\\"NONE\\\"  AuthRegMethodInfo=\\\"\\\" IsPreAuth=\\\"False\\\" PreAuthPage=\\\"\\\" DestinationSiteUrl=\\\"Authorized/WebAdmin.aspx\\\" ReturnUrl=\\\"\\\" TargetUrl=\\\"\\\" SAMLConsumerSiteUrl=\\\"\\\" SAMLRelayState=\\\"\\\" SAMLTargetUrl=\\\"\\\" Succeed=\\\"False\\\" Comment=\\\"Session Aborted\\\" AnalyzeEngineResult=\\\"\\\" BrowserSession=\\\"262f2d5f-4ad7-44f7-af6d-c1bedf3b3552\\\" StateMachineID=\\\"\\\" RequestID=\\\"91cc4e8d-cf7f-4694-956d-2761067f0f28\\\" Message=\\\"Session Aborted\\\"";
    private final Pattern csvPattern = Pattern.compile("\"([^\"]*)\"|(?<= |^)([^ ]*)(?: |$)");
    private ArrayList<String> allMatches = null;
    private Matcher matcher = null;
    private String match = null;


//  public ParseLogsTests() {
//      allMatches = new ArrayList<String>();
//      matcher = null;
//      match = null;
//  }

//  public static void main(String[] args){
//      String s = "LogChannel=\\\"SA_AUTHTRX\\\" FormatVersion=\\\"0.0.1\\\" EventID=\\\"20990\\\" Timestamp=\\\"2019-02-07T16:18:44.094Z\\\" CompanyID=\\\"3041ac47-6e50-458f-8a53-a9553795e683\\\" ApplianceID=\\\"33f0177f-ce65-e811-90bc-00505691306b\\\" ApplianceMachineName=\\\"SNA0QAPORTAL22\\\" Realm=\\\"SecureAuth0\\\" UserID=\\\"mtest1\\\" HashedUserID=\\\"o8Qy2x327qtUZZ7d1RieJRiNjIU=\\\" UserAgent=\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.96 Safari/537.36\\\" UserHostAddress=\\\"172.21.103.141\\\" ProductType=\\\"1\\\" ReceiveToken=\\\"4\\\" UseJava=\\\"False\\\" AllowedToken=\\\"BROWSERFINGERPRINT\\\" AuthGuiMode=\\\"1\\\"  AuthRegMethod=\\\"NONE\\\"  AuthRegMethodInfo=\\\"\\\" IsPreAuth=\\\"False\\\" PreAuthPage=\\\"\\\" DestinationSiteUrl=\\\"Authorized/WebAdmin.aspx\\\" ReturnUrl=\\\"\\\" TargetUrl=\\\"\\\" SAMLConsumerSiteUrl=\\\"\\\" SAMLRelayState=\\\"\\\" SAMLTargetUrl=\\\"\\\" Succeed=\\\"False\\\" Comment=\\\"Session Aborted\\\" AnalyzeEngineResult=\\\"\\\" BrowserSession=\\\"262f2d5f-4ad7-44f7-af6d-c1bedf3b3552\\\" StateMachineID=\\\"\\\" RequestID=\\\"91cc4e8d-cf7f-4694-956d-2761067f0f28\\\" Message=\\\"Session Aborted\\\"";
//      ParseLogsTests obj = new ParseLogsTests();
//      String[] strings_array = obj.parse(s);
//      for (String string:
//              strings_array) {
//          System.out.println(string);
//      }
       // System.out.println(strings_array.length);
//    }
  @Test
  public void testPrintMessage() {

      assertEquals(splits(testString).size(),68);
  }

    public List<String> splits (String message){
      List<String> matchList = new ArrayList<String>();
      matchList = Arrays.asList(message.split("\""));
      //Pattern regex = Pattern.compile("(\"[^\"]*\"|'[^']*'|[\\S]+)+");
      ////Pattern regex = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");
      //Matcher regexMatcher = regex.matcher(message);
      //while (regexMatcher.find()) {
      //    matchList.add(regexMatcher.group());
      //}

        for (String string:
             matchList) {
            System.out.println(string);
        }
      return matchList;
    }



    public String[] parse(String csvLine) {
        matcher = csvPattern.matcher(csvLine);
        allMatches.clear();
        String match;
        while (matcher.find()) {
            match = matcher.group(1);
            if (match!=null) {
                allMatches.add(match);
            }
            else {
                allMatches.add(matcher.group(2));
            }
        }
        List<String> results = new ArrayList<String>();
        for(int i = 0 ; i < allMatches.size(); i++ ) {
            if(!allMatches.get(i).equals("")) {
                results.add(allMatches.get(i).trim());
            }
        }
        if (results.size() > 0) {
            return results.toArray(new String[results.size()]);
        }
        else {
            return new String[0];
        }
    }

}
