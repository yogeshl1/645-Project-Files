public class Restaurant {
    private String name;
    private String menu;
    private boolean isPopular;

    public Restaurant(String name, String menu, boolean isPopular) {
        this.name = name;
        this.menu = menu;
        this.isPopular = isPopular;
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return menu;
    }

    public boolean isPopular() {
        return isPopular;
    }
}
