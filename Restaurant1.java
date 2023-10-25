import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private boolean isPopular;

    // getters and setters
}
