package com.example.justdoit.connect;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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

import android.net.Uri;
import android.util.Log;

import com.example.justdoit.GalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    protected static final String TAG = "WebAnt";

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";

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
            while((bytesRead = in.read(buffer)) > 0) {
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

    public List<GalleryItem> fetchItems() {

        List<GalleryItem> items = new ArrayList<>();

        try {
            URL url = new URL("http://gallery.dev.webant.ru/api/photos?page=1&limit=13");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();
            ////////////
            JSONObject jsonBody = new JSONObject(resultJson);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }
        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonBody) throws IOException, JSONException {

        try {
//
            JSONArray data = jsonBody.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {

                JSONObject datafriend = data.getJSONObject(i);
                JSONObject imagefriend = datafriend.getJSONObject("image");

                String imgid = imagefriend.getString("id");
                String contentUrl = imagefriend.getString("contentUrl");

                GalleryItem item = new GalleryItem();
                item.setId(imagefriend.getString("id"));
                item.setCaption(imagefriend.getString("contentUrl"));
                Log.e(TAG, "photoJsonArray");

                if (!imagefriend.has("contentUrl")) {
                    continue;
                }

                item.setUrl(imagefriend.getString("contentUrl"));
//                item.setUrl(imagefriend.getString("http://gallery.dev.webant.ru/media/"));
                items.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
