import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
}

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }
}
