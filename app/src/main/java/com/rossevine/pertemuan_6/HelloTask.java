package com.rossevine.pertemuan_6;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.HttpAuthHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossevine.pertemuan_6.MainActivity;
import com.rossevine.pertemuan_6.entity.StatusMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Developer on 2/20/2018.
 */

public class HelloTask extends AsyncTask<String, Void, StatusMessage> {
    private Context context;

    public HelloTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(StatusMessage statusMessage) {
        super.onPostExecute(statusMessage);
        ((MainActivity) context).updateView(statusMessage);
    }

    @Override
    protected StatusMessage doInBackground(String... strings) {
        Uri uri = Uri.parse("http://10.0.2.2/ws_mobile20172/service/hello_service.php")
                .buildUpon().appendQueryParameter("name",strings[0]).build();
        StatusMessage statusMessage = null;
        HttpURLConnection urlConnection = null;
        try {

            URL url= new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            System.out.println(responseCode);
            if(responseCode== HttpURLConnection.HTTP_OK){
                ObjectMapper objectMapper = new ObjectMapper();
                statusMessage =
                        objectMapper.readValue(url,StatusMessage.class);


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return statusMessage;
    }


}
