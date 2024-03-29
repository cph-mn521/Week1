/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.ExceptionDTO;
import errorhandling.ReeQuestException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin
 */
public class ReeQuest {

    final public String source;
    private String url;
    private HashMap<String, String> paths = new HashMap<String, String>();
    private StringBuilder response = new StringBuilder();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public ReeQuest(String source, String URL) throws MalformedURLException {
        this.source = source;
        this.url =URL;
        URL obj = new URL(url);
    }

    public void addPath(String path) {
        paths.put(path, path);
    }

    public String getRequest(String path, Map<String, String> parameters) throws ReeQuestException {
        StringBuilder response = new StringBuilder();
        BufferedReader in;
        String inputLine;
        try {
            URL obj = new URL(url + "/" + path);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());

            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (responseCode == 200) {
                return response.toString();
            } else {
               ExceptionDTO Err = new ExceptionDTO( responseCode, "fejl");
               return GSON.toJson(Err);
            }

        } catch (Exception e) {
            throw new ReeQuestException(e.getMessage());
        }

    }

    private String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getPaths() {
        return paths;
    }

    public void setPaths(HashMap<String, String> paths) {
        this.paths = paths;
    }
       
}


