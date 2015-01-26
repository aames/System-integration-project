package DataSources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Andrew
 */
public class WebFetcher {

    public WebFetcher() {
    }

    public String getSourceCode(String code) {
        try {
            String url = "http://www.staffs.ac.uk/current/student/modules/showmodule.php?code=";
            URL theURL = new URL(url.concat(code));
            URLConnection theConnection = theURL.openConnection();
            String encoding = theConnection.getContentEncoding();
            if (encoding == null) {
                encoding = "UTF8";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(theConnection.getInputStream(), encoding));
            StringBuilder sb = new StringBuilder(16384);
            try {
                String ln;
                while ((ln = br.readLine()) != null) {
                    sb.append(ln);
                    sb.append('\n');
                }
            } finally {
                br.close();
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getContactEmail(String code){
        String source = getSourceCode(code);
        String s[] = source.split("(?<=mailto:)");
        String splitString = s[1];
        String result = splitString.substring(0, splitString.indexOf("\""));
        
        return result;
    }
    public String getContact(String code){
        String source = getSourceCode(code);
        String split[];
        split = source.split("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
        String trimmed = split[0].substring(split[0].indexOf("VLE</b>"));
        String ret = trimmed.substring(trimmed.indexOf("<TD>"), trimmed.lastIndexOf("/"));
        String ret1 = ret.substring(4);
        String ret2 = ret1.substring(0, ret1.length()-1);
        return ret2;
    }
    
    public String getTitle(String code){
        String source = getSourceCode(code);
        String split = source.substring(source.indexOf("colspan=5>"));
        return split.substring(split.indexOf(">")+1,split.indexOf("<"));
    }
}