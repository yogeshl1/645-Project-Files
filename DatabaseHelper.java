import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "your_database_name";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MENU_ITEMS = "menu_items";
    private static final String COL_ITEM_NAME = "item_name";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        String createTableQuery = "CREATE TABLE " + TABLE_MENU_ITEMS + " (" +
                COL_ITEM_NAME + " TEXT, " +
                COL_DESCRIPTION + " TEXT, " +
                COL_PRICE + " REAL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_ITEMS);
        onCreate(db);
    }

    // Method to add a menu item to the database
    public void addMenuItem(String itemName, String description, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_MENU_ITEMS + " VALUES ('" +
                itemName + "', '" + description + "', " + price + ")";
        db.execSQL(insertQuery);
        db.close();
    }

    // Method to get all menu items from the database
    public List<MenuItems> getAllMenuItems() {
        List<MenuItems> menuItemsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU_ITEMS, null);

        if (cursor.moveToFirst()) {
            do {
                String itemName = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME));
                String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                double price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE));

                MenuItems menuItem = new MenuItems(itemName, description, price);
                menuItemsList.add(menuItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return menuItemsList;
    }

    // MenuItems class to represent the data
    public static class MenuItems {
        private String itemName;
        private String description;
        private double price;

        public MenuItems(String itemName, String description, double price) {
            this.itemName = itemName;
            this.description = description;
            this.price = price;
        }

        // Getter methods...
    }
}
