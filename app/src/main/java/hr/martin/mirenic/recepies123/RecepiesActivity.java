package hr.martin.mirenic.recepies123;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RecepiesActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;


    private static String url = "https://apex.oracle.com/pls/apex/dbp_project/mobileapp/recepies";

    ArrayList<HashMap<String, String>> recepieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepies);

        recepieList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new RecepiesActivity.GetRecepies().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetRecepies extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RecepiesActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray recepies = jsonObj.getJSONArray("items");

                    // looping through All recepies
                    for (int i = 0; i < recepies.length(); i++) {
                        JSONObject c = recepies.getJSONObject(i);

                        String id = c.getString("id");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String key_words = c.getString("key_words");
                        String dificulty = c.getString("dificulty");
                        String created_by = c.getString("created_by");
                        String creation_date = c.getString("creation_date");


                        HashMap<String, String> recepie = new HashMap<>();

                        // adding each child node to HashMap key => value
                        recepie.put("id", id);
                        recepie.put("title", title);
                        recepie.put("key_words", key_words);
                        recepie.put("dificulty", dificulty);
                        recepie.put("created_by", created_by);
                        recepie.put("creation_date", creation_date);


                        recepieList.add(recepie);
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
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    RecepiesActivity.this, recepieList,
                    R.layout.list_item_recepies, new String[]{"title", "key_words",
                    "dificulty"/*,"created_by","creation_date"*/}, new int[]{R.id.title,
                    R.id.key_words, R.id.dificulty,/* R.id.created_by, R.id.creation_date*/});

            lv.setAdapter(adapter);
        }

    }
}


