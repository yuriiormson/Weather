import java.util.Random;

public class Util {
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public static boolean containWord(String str, String word) {
        boolean isContain = false;
        String[] strArr = str.split(" ");
        for (String ignored : strArr){
            for (String s : strArr) {
                if (s.equals(word)) {
                    isContain = true;
                }
            }
        }

        return isContain;
    }
}
