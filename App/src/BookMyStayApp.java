import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 5);
        inventory.put("Suite", 5);
    }

    public boolean hasAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public void reduce(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class RoomAllocationService {
    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public String confirmBooking(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        if (!inventory.hasAvailable(roomType)) {
            return "No rooms available";
        }

        String roomId = generateRoomId(roomType);
        allocatedRoomIds.add(roomId);

        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        assignedRoomsByType.get(roomType).add(roomId);

        inventory.reduce(roomType);
        return roomId;
    }

    private String generateRoomId(String roomType) {
        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        int next = assignedRoomsByType.get(roomType).size() + 1;
        return roomType + "-" + next;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        RoomAllocationService service = new RoomAllocationService();
        RoomInventory inventory = new RoomInventory();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subho", "Single");
        Reservation r3 = new Reservation("Vannathi", "Suite");

        String id1 = service.confirmBooking(r1, inventory);
        System.out.println("Booking confirmed for Guest: " + r1.getGuestName() + ", Room ID: " + id1);

        String id2 = service.confirmBooking(r2, inventory);
        System.out.println("Booking confirmed for Guest: " + r2.getGuestName() + ", Room ID: " + id2);

        String id3 = service.confirmBooking(r3, inventory);
        System.out.println("Booking confirmed for Guest: " + r3.getGuestName() + ", Room ID: " + id3);
    }
}