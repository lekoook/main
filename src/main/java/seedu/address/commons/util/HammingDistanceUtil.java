package seedu.address.commons.util;

public class HammingDistanceUtil {
    private String left, right;

    public HammingDistanceUtil (String left, String right) {
        this.left = left.toLowerCase();
        this.right = right.toLowerCase();
    }

    public int getDistance () {
        int distance = 0;
        if (left.length() < right.length()) {
            for (int i = 0; i < left.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    distance++;
                }
            }
        } else {
            for (int i = 0; i < right.length(); i++) {
                if (left.charAt(i) != right.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

}