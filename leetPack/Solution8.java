package leetPack;

import java.util.*;

public class Solution8 {
    // 752. 打开转盘锁
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        // 特殊情况
        if (deadSet.contains(target)){
            return -1;
        }
        if (deadSet.contains("0000")){
            return -1;
        }
        if (target.equals("0000")){
            return 0;
        }

        // BFS
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        queue.add("0000");
        visited.put("0000", 0);
        while (!queue.isEmpty()){
            String currentString = queue.remove();
            ArrayList<String> nextString = new ArrayList<>();
            char[] currentArray = currentString.toCharArray();
            for (int i=0;i<4;i++){
                char original = currentArray[i];
                currentArray[i] = Character.forDigit((currentArray[i] - '0' + 1) % 10, 10);
                nextString.add(new String(currentArray));
                currentArray[i] = original;
                currentArray[i] = Character.forDigit((currentArray[i] - '0' + 9) % 10, 10);
                nextString.add(new String(currentArray));
                currentArray[i] = original;
            }
            for (String next: nextString){
                if (!deadSet.contains(next) && !visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(currentString) + 1);
                    if (next.equals(target)){
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }
}
