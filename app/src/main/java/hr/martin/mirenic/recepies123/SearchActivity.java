package hr.martin.mirenic.recepies123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    ArrayList<HashMap<String, String>> recepieList = new ArrayList<>();

    private static String url = "https://apex.oracle.com/pls/apex/dbp_project/mobileapp/recepies";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1);
        lv.setAdapter(adapter);

        // ListView setOnItemClickListener function apply here.

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(SearchActivity.this, RecepieDetailActivity.class);

                HashMap<String,String> recepie = recepieList.get(position);

                intent.putExtra("id", recepie.get("id"));
                intent.putExtra("title", recepie.get("title"));
                intent.putExtra("keyWords", recepie.get("keyWords"));
                intent.putExtra("dificulty", recepie.get("dificulty"));
                intent.putExtra("createdBy", recepie.get("createdBy"));
                intent.putExtra("creationDate", recepie.get("creationDate"));
                intent.putExtra("description", recepie.get("description"));
                intent.putExtra("text", recepie.get("text"));
                startActivity(intent);
            }
        });
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SearchActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = jsonObj.getJSONArray("items");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);


                        String id = c.getString("id");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String keyWords = c.getString("key_words");
                        String dificulty = c.getString("dificulty");
                        String createdBy = c.getString("created_by");
                        String creationDate = c.getString("creation_date");
                        String text = c.getString("text");

                        HashMap<String, String> recepie = new HashMap<>();

                        // adding each child node to HashMap key => value
                        recepie.put("id", id);
                        recepie.put("title", title);
                        recepie.put("keyWords", keyWords);
                        recepie.put("dificulty", dificulty);
                        recepie.put("createdBy", createdBy);
                        recepie.put("creationDate", creationDate);
                        recepie.put("description", description);
                        recepie.put("text", text);

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

            if (pDialog.isShowing())
                pDialog.dismiss();


            ListAdapter adapter = new SimpleAdapter(
                    SearchActivity.this, recepieList,
                    R.layout.list_item_recepies, new String[]{"title", "keyWords",
                    "dificulty", "createdBy", "creationDate"}, new int[]{R.id.title,
                    R.id.key_words, R.id.dificulty, R.id.createdBy, R.id.creationDate});

            lv.setAdapter(adapter);
        }

    }
}
