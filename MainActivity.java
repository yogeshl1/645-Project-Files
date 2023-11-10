import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView restaurantListView;
    private ArrayList<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantListView = findViewById(R.id.restaurantListView);
        restaurantList = new ArrayList<>();

        // Fetch restaurant data from API
        new FetchRestaurantsTask().execute("API_ENDPOINT_URL");
    }

   private class FetchMenuItemsTask extends AsyncTask<Long, Void, String> {
    @Override
    protected String doInBackground(Long... restaurantIds) {
        try {
            // Construct the API endpoint URL for fetching menu items based on restaurant ID
            String menuItemsUrl = "MENU_ITEMS_API_ENDPOINT_URL" + restaurantIds[0];
            URL url = new URL(menuItemsUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String menuItemsResponse) {
        if (menuItemsResponse != null) {
            try {
                // Parse menu items JSON response and handle accordingly
                JSONArray menuItemsArray = new JSONArray(menuItemsResponse);
                // Handle menu items data as needed, e.g., update UI or data structures
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}


        @Override
protected void onPostExecute(String response) {
    if (response != null) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String menu = jsonObject.getString("menu");
                boolean isPopular = jsonObject.getBoolean("isPopular");

                Restaurant restaurant = new Restaurant(name, menu, isPopular);
                restaurantList.add(restaurant);
            }

            RestaurantAdapter adapter = new RestaurantAdapter(MainActivity.this, R.layout.restaurant_item, restaurantList);
            restaurantListView.setAdapter(adapter);

            // Fetch menu items for the first restaurant (assuming you have restaurant IDs in your Restaurant class)
            if (!restaurantList.isEmpty()) {
                long restaurantId = restaurantList.get(0).getId();
                new FetchMenuItemsTask().execute(restaurantId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

        }
    }
}
