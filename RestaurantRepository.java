import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
