package cn.alvinkwok.codetool.notice;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DingTalk {

    //private static final String DINGTALK_WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=6d9d3a45f7177b6890973d181f988b52fa007b2d65833e48bede7635ad01eb8a";
    private static final String DINGTALK_WEBHOOK_URL = "https://oapi.dingtalk.com/robot/send?access_token=358b476d63ddd2e3722b69d51f71165ecd0cd1644e5c98315da1e0f51d08ad87";
    // https://oapi.dingtalk.com/robot/send?access_token=358b476d63ddd2e3722b69d51f71165ecd0cd1644e5c98315da1e0f51d08ad87

    public static void sendDingTalkMessage(String message) throws Exception {
        URL url = new URL(DINGTALK_WEBHOOK_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        String jsonPayload = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + message + "\"}}";

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonPayload.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            System.out.println("Message sent successfully");
        } else {
            System.out.println("Failed to send message: " + responseCode);
        }
    }

    public static void main(String[] args) {
        try {
            sendDingTalkMessage("测试：Hello, DingTalk!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}