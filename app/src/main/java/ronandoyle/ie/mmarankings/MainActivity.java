package ronandoyle.ie.mmarankings;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    private MainActivityFragment mFragment;
    private ArrayList<Fighter> mFighterArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFighterArrayList = new ArrayList<>();


    }

    @Override
    public void onResume() {
        super.onResume();

        mFragment = (MainActivityFragment) getFragmentManager().findFragmentByTag(MainActivityFragment.FRAGMENT_TAG);

        if (mFragment == null) {
            mFragment = new MainActivityFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.frame_layout, mFragment, MainActivityFragment.FRAGMENT_TAG);
            fragmentTransaction.commit();
        }

        GetAllFighters getAllFighters = new GetAllFighters();
        getAllFighters.execute("http://ufc-data-api.ufc.com/api/v3/iphone/fighters");

        GetData getData = new GetData(this);
        getData.execute("http://ufc-data-api.ufc.com/api/v3/iphone/fighters/title_holders");

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

    private class GetAllFighters extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);

                // Create the request to OpenWeatherMap, and open the connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                String jsonData = buffer.toString();

                JSONArray jsonArray = new JSONArray(jsonData);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    private class GetData extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog dialog;

        public GetData(Context context) {
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            this.dialog.setMessage("Progress start");
            this.dialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... params) {

            Pattern pattern = Pattern.compile("^_");
            Matcher matcher;
            URL url = null;
            try {
                url = new URL(params[0]);
                // Create the request to OpenWeatherMap, and open the connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                String jsonData = buffer.toString();

                JSONArray jsonArray = new JSONArray(jsonData);

                final String ID = "id";
                final String NICKNAME = "nickname";
                final String FIRSTNAME = "first_name";
                final String LASTNAME = "last_name";
                final String THUMBNAIL = "thumbnail";
                final String RECORD_WINS = "wins";
                final String RECORD_LOSSES = "losses";
                final String RECORD_DRAWS = "wins";
                final String WEIGHTCLASS = "weight_class";

                for (int i = 0; i < jsonArray.length(); i++) {
                    Fighter newFighter = new Fighter();
                    JSONObject fighterObject = jsonArray.getJSONObject(i);
                    newFighter.setId(fighterObject.getInt(ID));
                    newFighter.setFirstName(fighterObject.getString(FIRSTNAME));
                    newFighter.setLastName(fighterObject.getString(LASTNAME));
                    newFighter.setNickname(fighterObject.getString(NICKNAME));
                    newFighter.setWins(fighterObject.getInt(RECORD_WINS));
                    newFighter.setLosses(fighterObject.getInt(RECORD_LOSSES));
                    newFighter.setDraws(fighterObject.getInt(RECORD_DRAWS));

                    newFighter.setWeightClass(fighterObject.getString(WEIGHTCLASS).replaceAll("_", " "));

                    String thumbNail = fighterObject.getString(THUMBNAIL);
                    thumbNail = thumbNail.substring(0, thumbNail.lastIndexOf("?"));
                    newFighter.setThumbnail(thumbNail);
                    mFighterArrayList.add(newFighter);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (!mFighterArrayList.isEmpty()) {
                mFragment.updateListView(mFighterArrayList);
            }
        }

    }
}