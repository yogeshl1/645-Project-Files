import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private List<Restaurant> restaurants;
    private int layoutResource;

    public RestaurantAdapter(Context context, int resource, List<Restaurant> restaurants) {
        super(context, resource, restaurants);
        this.restaurants = restaurants;
        this.layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layoutResource, null);
        }

        Restaurant restaurant = restaurants.get(position);

        if (restaurant != null) {
            TextView nameTextView = row.findViewById(R.id.nameTextView);
            TextView menuTextView = row.findViewById(R.id.menuTextView);
            TextView popularityTextView = row.findViewById(R.id.popularityTextView);

            nameTextView.setText(restaurant.getName());
            menuTextView.setText(restaurant.getMenu());

            if (restaurant.isPopular()) {
                popularityTextView.setText("Popular!");
            } else {
                popularityTextView.setText("");
            }
        }

        return row;
    }
}
