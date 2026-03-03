import java.util.HashMap;
import java.util.Map;

class BookMyStayApp{
    private Map<String, Integer> roomAvailability;

    public BookMyStayApp() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class UC3 {
    public static void main(String[] args) {
        BookMyStayApp inventory = new BookMyStayApp();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Hotel Room Inventory Status");
        System.out.println();

        displayRoomInfo("Single Room", 1, 250, 1500.0, availability.get("Single Room"));
        displayRoomInfo("Double Room", 2, 400, 2500.0, availability.get("Double Room"));
        displayRoomInfo("Suite Room", 3, 750, 5000.0, availability.get("Suite Room"));
    }

    private static void displayRoomInfo(String type, int beds, int size, double price, int available) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }
}
