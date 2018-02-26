package com.rossevine.pertemuan_6;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossevine.pertemuan_6.entity.StatusMessage;
import com.rossevine.pertemuan_6.entity.User;
import com.rossevine.pertemuan_6.entity.UserWrapper;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Developer on 2/20/2018.
 */

public class LoginTask extends AsyncTask<String,Void,UserWrapper> {
    private Context context;

    public LoginTask(Context context) {
        this.context = context;
    }

    @Override
    protected UserWrapper doInBackground(String... strings) {
        Uri uri = Uri.parse("http://10.0.2.2/ws_mobile20172/service/user_login_service.php")
                .buildUpon().build();
        UserWrapper userWrapper = null;
        HttpURLConnection urlConnection = null;
        try {

            URL url= new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            StringBuffer buffer = new StringBuffer("username=")
                    .append(URLEncoder.encode(strings[0],"UTF-8"))
                    .append("&password=")
                    .append(URLEncoder.encode(strings[1],"UTF-8"));
            System.out.println("heheh "+buffer.toString());

            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(buffer.toString());
            outputStream.flush();
            outputStream.close();

            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            System.out.println(responseCode);
            if(responseCode== HttpURLConnection.HTTP_OK){
                ObjectMapper objectMapper = new ObjectMapper();
                userWrapper =
                        objectMapper.readValue(url,UserWrapper.class);


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return userWrapper;
    }

    @Override
    protected void onPostExecute(UserWrapper userWrapper) {
        super.onPostExecute(userWrapper);
        ((LoginActivity) context).updateView(userWrapper);
    }
}
