public interface ApiService {

    @GET("api/restaurants")
    Call<List<Restaurant>> getRestaurants();

    @GET("api/menu/{restaurantId}")
    Call<List<MenuItem>> getMenuItems(@Path("restaurantId") long restaurantId);
}
