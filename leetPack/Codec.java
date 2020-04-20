package leetPack;

import java.util.HashMap;
import java.util.HashSet;

public class Codec {

    // Encodes a URL to a shortened URL.
    private HashMap<String, String> map = new HashMap<>();
    public String encode(String longUrl) {
        while (true){
            String shortUrl = "http://tinyurl.com/" + Integer.toHexString(longUrl.hashCode() + (int)(Math.random()*13) );
            if (!map.containsKey(shortUrl)){
                map.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.encode("https://leetcode.com/problems/design-tinyurl"));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));