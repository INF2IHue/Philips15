package com.android.android.philips;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by NATHAN on 30-3-2015.
 */
public class Connect {

    public JSONArray GetAllInfo()
    {
        String Overzicht = "http://192.168.0.50:21/phpconnect/Server/Overzicht.php";
        HttpEntity httpEntity = null;

        try{

            DefaultHttpClient httpClient = new DefaultHttpClient(); //Default http client
            HttpGet httpGet = new HttpGet(Overzicht);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();
        } catch (ClientProtocolException e){

            //Signals error in http protocol
            e.printStackTrace();

            //Log errors here
        } catch (IOException e){
            e.printStackTrace();
        }

        //Convert http entity into JSON Array
        JSONArray jsonArray = null;

        if(httpEntity != null){
            try{
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);
            } catch (JSONException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        return jsonArray;
    }
}
