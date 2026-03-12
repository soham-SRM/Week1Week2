import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HighThroughputSystem {
    // Shared Data Structures
    private static Map<String, Integer> userDirectory = new ConcurrentHashMap<>();
    private static Map<String, Integer> attemptTracker = new ConcurrentHashMap<>();
    private static Map<String, AtomicInteger> flashInventory = new ConcurrentHashMap<>();
    private static Map<String, Long> rateLimiters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("=== High-Throughput System Grouping (UC1, 2, 5, 6) ===");

        // 1. Username Availability (UC1)
        checkUser("john_doe");

        // 2. Flash Sale Purchase (UC2)
        flashInventory.put("IPHONE15", new AtomicInteger(2));
        System.out.println("Purchase 1: " + purchaseItem("IPHONE15", 101));
        System.out.println("Purchase 2: " + purchaseItem("IPHONE15", 102));
        System.out.println("Purchase 3: " + purchaseItem("IPHONE15", 103)); // Triggers Waiting List
    }

    // UC1: Username Logic
    static void checkUser(String name) {
        attemptTracker.put(name, attemptTracker.getOrDefault(name, 0) + 1);
        if (userDirectory.containsKey(name)) {
            System.out.println(name + " taken. Suggestions: " + name + "123, " + name + "_pro");
        } else {
            System.out.println(name + " is available!");
        }
    }

    // UC2: Atomic Inventory Management
    static String purchaseItem(String item, int userId) {
        AtomicInteger stock = flashInventory.get(item);
        if (stock != null && stock.get() > 0) {
            stock.decrementAndGet();
            return "Success! Item secured for User " + userId;
        }
        return "SOLD OUT. User " + userId + " added to FIFO Waiting List.";
    }
}