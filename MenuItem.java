import javax.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long restaurantId;
    private String name;
    private double price;
    private String description;

    // getters and setters
}
