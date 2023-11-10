private class FetchMenuItemsTask extends AsyncTask<Long, Void, String> {
    @Override
    protected String doInBackground(Long... restaurantIds) {
        try {
            // Make API request to fetch menu items by restaurantId
            URL url = new URL("API_MENU_ENDPOINT_URL" + restaurantIds[0]); // Replace with your API endpoint URL
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
    protected void onPostExecute(String response) {
        if (response != null) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                List<MenuItem> menuItems = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    double price = jsonObject.getDouble("price");
                    String description = jsonObject.getString("description");

                    MenuItem menuItem = new MenuItem(name, price, description);
                    menuItems.add(menuItem);
                }

                // Now you have the list of menu items, update the UI or perform further actions
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
