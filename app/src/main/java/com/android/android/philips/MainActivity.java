package com.android.android.philips;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = (TextView) this.findViewById(R.id.textView);

        new GetAllInfo().execute(new Connect());
    }

    public void setTextToTextView(JSONArray jsonArray){

        String s = "";
        for (int i=0;i<jsonArray.length();i++){
            JSONObject json = null;
            try{
                json = jsonArray.getJSONObject(i);
                s=s+
                        json.getString("informatie");

            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        this.textView.setText(s);
    }

    private class GetAllInfo extends AsyncTask<Connect,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(Connect... params) {

            //It is executed on background thread

            return params[0].GetAllInfo();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            setTextToTextView(jsonArray);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
