package stu.napls.nabootsocket;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicTest {

    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "ding");
        String mapString = map.toString();
        System.out.println(mapString);
        Map<String, String> map1 = convertWithStream(mapString);
        System.out.println(map1.get("name"));
    }

    public Map<String, String> convertWithStream(String mapAsString) {
        return Arrays.stream(mapAsString.replace("{","").replace("}","").split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
    }
}
