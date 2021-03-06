package leetPack.Recursive;

import java.util.*;

public class Backtracking {
    // 17. 电话号码的字母组合
    List<String> letterRes = new ArrayList<>();
    Map<Character, String> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) return letterRes;
        map.put('2', "abc"); map.put('3', "def"); map.put('4', "ghi");
        map.put('5', "jkl"); map.put('6', "mno"); map.put('7', "pqrs");
        map.put('8', "tuv"); map.put('9', "wxyz");
        findCombination(digits, 0, "");
        return letterRes;
    }
    private void findCombination(final String digits, int index, final String s){
        if (index == digits.length()){
            letterRes.add(s);
            return;
        }
        char c = digits.charAt(index);
        assert c >= '0' && c <='9' && c != '1';
        String letters = map.get(c);
        for (int i=0;i<letters.length();i++){
            findCombination(digits, index + 1, s + letters.charAt(i));
        }
    }

    // 93. 复原IP地址 **
    List<String> list = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        turnStep(s, 0, 0, new StringBuilder());
        return list;
    }
    private void turnStep(String s, int start, int pos, StringBuilder builder){
        if (pos == 4){
            if (start == s.length()){
                list.add(builder.toString().substring(0, builder.length() - 1));
            }
            return;
        }
        for (int i=start;i<start+3&&i<s.length();i++){
            String sub = s.substring(start, i + 1);
            int num = Integer.parseInt(sub);
            if (num > 255) continue;
            builder.append(s.substring(start, i + 1) + ".");
            turnStep(s, i + 1, pos +1, builder);
            builder.delete(builder.length() - (i - start + 2), builder.length());
            if (s.charAt(start) == '0') break;
        }
    }

    // 131. 分割回文串 ***
    public List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>> lists = new ArrayList<>();
        if (length == 0){
            return lists;
        }
        Deque<String> deque = new ArrayDeque<>();
        backtracking(s, 0, length, deque, lists);
        return lists;
    }
    private void backtracking(String s, int start, int length, Deque<String> path, List<List<String>> lists){
        if (start == length){
            lists.add(new ArrayList<>(path));
        }
        for (int i=start;i<length;i++){
            if (!checkPalindrome(s, start, i)){
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, length, path, lists);
            path.removeLast();
        }
    }
    private boolean checkPalindrome(String str, int left, int right){
        while (left < right){
            if (str.charAt(left) != str.charAt(right)){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }

    // 46. 全排列
    List<List<Integer>> permute;
    public List<List<Integer>> permute(int[] nums) {
        permute = new ArrayList<>();
        if (nums.length == 0){
            return permute;
        }
        generatePermutation(nums, 0, new ArrayList<>());
        return permute;
    }
    private void generatePermutation(final int[] nums, int index, List<Integer> p){
        if (index == nums.length){
            permute.add(new ArrayList<>(p));
            return;
        }
        for (int num : nums) {
            if (!p.contains(num)) {
                p.add(num);
                generatePermutation(nums, index + 1, p);
                p.remove(p.size()-1);
            }
        }
    }

    // 77. 组合 1...n select count k
    List<List<Integer>> combine;
    public List<List<Integer>> combine(int n, int k) {
        combine = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n){
            return combine;
        }
        generateCombinations(n, k, 1, new ArrayList<>());
        return combine;
    }
    // C(n, k) from start
    private void generateCombinations(int n, int k, int start, List<Integer> c){
        if (c.size() == k){
            // save
            combine.add(new ArrayList<>(c));
            return;
        }
        for (int i=start;i<=n-(k-c.size())+1;i++){
            c.add(i);
            generateCombinations(n, k, i + 1, c);
            c.remove(c.size() - 1);
        }
    }

    // 39. 组合总和
    List<List<Integer>> combinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSum = new ArrayList<>();
        assert candidates.length >= 1 && candidates.length <= 30;
        assert target >= 1 && target <= 500;
        Arrays.sort(candidates);
        generateCombinationSum(candidates, target, new ArrayList<>(), 0, 0);
        return combinationSum;
    }
    private void generateCombinationSum(int[] candidates, int target, List<Integer> cs, int sum, int start){
        if (sum == target){
            combinationSum.add(new ArrayList<>(cs));
            return;
        }
        for (int i=start;i<candidates.length;i++){
            if (sum + candidates[i] > target) break;
            cs.add(candidates[i]);
            generateCombinationSum(candidates, target, cs, sum + candidates[i], i);
            cs.remove(cs.size() - 1);
        }
    }

    // 79. 单词搜索
    private int m, n;
    private boolean[][] visited;
    private int[][] direct = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public boolean exist(char[][] board, String word) {
        m = board.length;
        assert m > 0;
        n = board[0].length;
        visited = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        for(int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (searchWord(board, word, 0, i, j)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean searchWord(char[][] board, final String word, int index, int startX, int startY){
        if (index == word.length() - 1){
            return board[startX][startY] == word.charAt(index);
        }
        if (board[startX][startY] == word.charAt(index)){
            visited[startX][startY] = true;
            for (int[] d: direct){
                int newX = startX + d[0];
                int newY = startY + d[1];
                if (inArea(newX, newY) && !visited[newX][newY] && searchWord(board, word, index + 1, newX, newY)){
                    return true;
                }
            }
            visited[startX][startY] = false;
        }
        return false;
    }
    private boolean inArea(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // 200. 岛屿数量
//    private int m, n;
//    private boolean[][] visited;
//    private int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        int result = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }
    private void dfs(char[][] grid, int x, int y){
        visited[x][y] = true;
        for (int i=0;i<4;i++){
            int newX = x + direct[i][0];
            int newY = y + direct[i][1];
            if(inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1'){
                dfs(grid, newX, newY);
            }
        }
    }
//    private boolean inArea(int x, int y){
//        return x >= 0 && x < m && y >= 0 && y < n;
//    }

    // 51. N 皇后
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private ArrayList<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<List<String>>();
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        LinkedList<Integer> row = new LinkedList<Integer>();
        putQueen(n, 0, row);
        return res;
    }
    private void putQueen(int n, int index, LinkedList<Integer> row){
        if(index == n){
            res.add(generateBoard(n, row));
            return;
        }
        for(int i = 0 ; i < n ; i ++)
            if(!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]){
                row.addLast(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueen(n, index + 1, row);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
    }
    private List<String> generateBoard(int n, LinkedList<Integer> row){
        assert row.size() == n;
        ArrayList<String> board = new ArrayList<String>();
        for(int i = 0 ; i < n ; i ++){
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[row.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }
}
