package com.example.justdoit.connect;

import android.util.Log;

import com.example.justdoit.GalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    private static final String TAG = "Connection";

    private String response;

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems(String url) {

        List<GalleryItem> items = new ArrayList<>();

        try {
            response = new FlickrFetchr()
                    .getUrlString(url);
            Log.i(TAG, "JSON STRING: " + response);
            JSONObject jsonBody = new JSONObject(response);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch URL: ", ioe);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) {
        JSONObject dataJsonObject;

        try {
            dataJsonObject = new JSONObject(response);
            JSONArray data = dataJsonObject.getJSONArray("data");
            Log.d(TAG, "data: " + data);

            for (int i = 0; i < data.length(); i++) {
                JSONObject img = data.getJSONObject(i);
                JSONObject image = img.getJSONObject("image");

                GalleryItem item = new GalleryItem();

                String contentUrl = image.getString("contentUrl");

                item.setUrl(item.getUrl() + contentUrl);
                items.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
