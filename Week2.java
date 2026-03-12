import java.util.*;

public class DataOptimizationSystem {

    // UC10: Multi-Level Cache using LinkedHashMap (LRU Strategy)
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;
        public LRUCache(int cap) { super(cap, 0.75f, true); this.capacity = cap; }
        protected boolean removeEldestEntry(Map.Entry eldest) { return size() > capacity; }
    }

    public static void main(String[] args) {
        System.out.println("=== Data Optimization System Grouping (UC3, 4, 7, 9, 10) ===");

        // 1. Two-Sum Financial Fraud Detection (UC9)
        int[] transactions = {200, 500, 300, 1000};
        findFraudPairs(transactions, 500);

        // 2. Multi-Level Cache Logic (UC3/UC10)
        LRUCache<String, String> l1Memory = new LRUCache<>(2);
        l1Memory.put("dns:google.com", "172.217.14.206");
        l1Memory.put("dns:github.com", "140.82.112.4");

        System.out.println("L1 DNS Lookup (Google): " + l1Memory.get("dns:google.com"));
    }

    // UC9: O(n) Two-Sum using Hash Table
    static void findFraudPairs(int[] amounts, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int amt : amounts) {
            int complement = target - amt;
            if (map.containsKey(complement)) {
                System.out.println("Fraud Alert: Found pair summing to " + target + " (" + amt + " + " + complement + ")");
            }
            map.put(amt, 1);
        }
    }

    // UC4: Simple N-Gram Plagiarism Logic
    static void checkSimilarity(String text) {
        // In a real system, we'd hash 5-word sequences (n-grams)
        // and check frequency in a Global HashMap.
        System.out.println("Analyzing n-grams for plagiarism...");
    }
}