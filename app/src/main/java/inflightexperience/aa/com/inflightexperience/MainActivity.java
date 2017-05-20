package inflightexperience.aa.com.inflightexperience;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.AsyncTask;
import android.util.Base64;

import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;

import inflightexperience.aa.com.inflightexperience.datamodels.Beacon;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private static String url="https://trappist1p1942636699trial.hanatrial.ondemand.com/AmericanAirlines/services/flights.xsodata/passengers?$format=json";

    private static String beaconUrl="https://cube.api.aero/atibeacon/beacons/1?airportCode=SITA_XS&app_id=e719dcad&app_key=cedf92f7a02fd4e8cb585ba7477d9b19&preservePendingNewBeacons=true";

    private static HashMap<String,Beacon> beaconsInfo;
    private ProgressDialog pDialog;
    private ListView lv;

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        beaconsInfo=new HashMap<>();

        lv = (ListView) findViewById(R.id.list);
      //  GetPassengerAsyncTask aTask = new GetPassengerAsyncTask();
        //aTask.execute();

        GetBeaconInfo aTask1=new GetBeaconInfo();
        aTask1.execute();
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

class GetBeaconInfo extends AsyncTask<Void,Void,String>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        HttpHandler sh=new HttpHandler();
        String jsonStr=sh.getPassengerData(beaconUrl);
        Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONArray jsonObj = new JSONArray(jsonStr);

                for(int i=0;i<jsonObj.length();i++) {
                    Beacon b = new Beacon();

                    JSONObject record=jsonObj.getJSONObject(i);
                    String name = record.getString("name");
                    String UUID = record.getString("uuid");
                    Integer majorId = record.getInt("majorId");
                    Integer minorId = record.getInt("minorId");
                    Integer power = record.getInt("power");

                    b.setName(name);
                    b.setUUID(UUID);
                    b.setMajorId(majorId);
                    b.setMinorId(minorId);
                    b.setPower(power);
                    beaconsInfo.put(name, b);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
       Log.d(TAG,beaconsInfo.toString());
    }

}

    class GetPassengerAsyncTask extends AsyncTask<Void, Void, String> {

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             // Showing progress dialog
             pDialog = new ProgressDialog(MainActivity.this);
             pDialog.setMessage("Please wait...");
             pDialog.setCancelable(false);
             pDialog.show();

         }

         @Override
         protected String  doInBackground(Void... params) {

             HttpHandler sh = new HttpHandler();

             // Making a request to url and getting response
             String jsonStr = sh.getPassengerData(url);

             Log.e(TAG, "Response from url: " + jsonStr);

             if (jsonStr != null) {
                 try {
                     JSONObject jsonObj = new JSONObject(jsonStr);

                     // Getting JSON Array node
                     JSONObject DObj = jsonObj.getJSONObject("d");

                     Log.d(TAG,DObj.toString());

                     JSONArray Results= DObj.getJSONArray("results");

                    // Log.d(TAG,"Here"+Results.toString());

                    // JSONArray Results=Result.getJSONArray("result");


                     // looping through All Contacts
                     for (int i = 0; i < Results.length(); i++) {
                         JSONObject c = Results.getJSONObject(i);
                         //Log.d(TAG,c.toString());
                        // JSONObject d=c.getJSONObject("__metadata");
                        // Log.d(TAG,d.toString());
                         String id = c.getString("CUSTOMERID");
                         String food = c.getString("FOOD");
                         String drink = c.getString("DRINK");

                        // Log.d(TAG,id+food+drink);


                         HashMap<String,String> contact=new HashMap<String,String>();
                         // adding each child node to HashMap key => value
                         contact.put("customerid", id);
                         contact.put("food",food );
                         contact.put("drink", drink);

                         // adding contact to contact list
                         contactList.add(contact);
                     }
                 } catch (final JSONException e) {
                     Log.e(TAG, "Json parsing error: " + e.getMessage());
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             Toast.makeText(getApplicationContext(),
                                     "Json parsing error: " + e.getMessage(),
                                     Toast.LENGTH_LONG)
                                     .show();
                         }
                     });

                 }
             } else {
                 Log.e(TAG, "Couldn't get json from server.");
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         Toast.makeText(getApplicationContext(),
                                 "Couldn't get json from server. Check LogCat for possible errors!",
                                 Toast.LENGTH_LONG)
                                 .show();
                     }
                 });

             }

             return null;

         }

         @Override
         protected void onPostExecute(String result) {
             super.onPostExecute(result);
             // Dismiss the progress dialog
             if (pDialog.isShowing())
                 pDialog.dismiss();
             /**
              * Updating parsed JSON data into ListView
              * */
             ListAdapter adapter = new SimpleAdapter(
                     MainActivity.this, contactList,
                     R.layout.list_item, new String[]{"customerid", "food",
                     "drink"}, new int[]{R.id.customerid,
                     R.id.food, R.id.drink});

             lv.setAdapter(adapter);
         }

     }
}
