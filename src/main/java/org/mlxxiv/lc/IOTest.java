package org.mlxxiv.lc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOTest {
    public static void main(String[] args) {
        parseJson();
        String fileName = "/Users/abystrytskyi/Desktop/CV/AlexBystrytskyi_CV.pdf";
        try {
//            readFile(fileName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line =  bufferedReader.readLine();
        while (line != null ) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public static void readUrl(String address) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("version", "1");

        URL url = new URL(address);
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");


        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.writeBytes(getParamsString(params));

        String contentType = conn.getHeaderField("Content-Type");
        String cookiesHeader = conn.getHeaderField("Set-Cookie");
        List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);

        out.flush();
        out.close();
    }

    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder paramsString = new StringBuilder();

        for (Map.Entry<String, String> param: params.entrySet()) {
            paramsString.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            paramsString.append("=");
            paramsString.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            paramsString.append("&");
        }
        return params.size() > 0 ? paramsString.toString().substring(0, paramsString.toString().length()-1) : "";
    }

    public static void parseJson() {
        String jsonString = "{\"name\":\"Alex\", \"age\":99}";
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
        Gson gson = new GsonBuilder().create();

        HashMap<String, Object> map = gson.fromJson(jsonString, HashMap.class);

        for (Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


    }
}
