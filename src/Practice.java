import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {

        int oddCount = 0;

        if(nums == null) return 0;

        for(int items: nums)
        {
            if(items % 2 != 0)
            {
                oddCount += items;
            }
        }

        return oddCount;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {

        String smallestWord = null;

        if(words == null) throw new NullPointerException();
        if(words.isEmpty()) throw new IllegalArgumentException();

        for(String items: words)
        {
            if(smallestWord == null || items.length() < smallestWord.length() ||
            (items.length() == smallestWord.length()) && items.compareTo(smallestWord) < 0)
             {
                smallestWord = items;
             }
            
        }
        

        return smallestWord;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if(ages == null) throw new NullPointerException();
        Set<String> people = new HashSet<>();

        for(Map.Entry<String, Integer> items : ages.entrySet())
        {
            if(items.getValue() >= 18)
            {
                people.add(items.getKey());
            }
        }


        return people;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        
        if(head == null) throw new IllegalArgumentException();
        
        int biggest = Integer.MIN_VALUE;
        ListNode<Integer> current = head;
        while(current != null)
        {
            if(biggest < current.data)
            {
                biggest = current.data;
            }
            current = current.next;
        }

        return biggest;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y: 1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        Map<T, Integer> map = new HashMap<>();
        

        if(head == null) return map;
        ListNode<T> current = head;
        while(current != null) 
        {
            map.put(current.data, map.getOrDefault(current.data, 0) + 1);
            current = current.next;
        }


        return map;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if(root == null) return 0;
        
        int leftSearch = levelCount(root.left);
        int rightSearch = levelCount(root.right);


        return Math.max(leftSearch, rightSearch) + 1;
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if(root == null) return 0;

        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        int target = 1;

        while(!q.isEmpty())
        {
            int size = q.size();
            int sumed = 0;

            for(int i = 0; i < size; i++)
            {
                BinaryTreeNode<Integer> current = q.poll();
                if(target == level) sumed += current.data;
                if(current.left != null) q.add(current.left);  
                if(current.right != null) q.add(current.right);  
            }

            if(target == level) return sumed;
            target++;
            
        }
        return 0;
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {

        if(head == null && root == null) return true;
        if(root == null) return false;
        if(head == null) return false;

        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        int BSTSum = 0;

        while(!q.isEmpty())
        {
            BinaryTreeNode<Integer> current = q.poll();
            BSTSum += current.data;
            if(current.left != null) q.add(current.left);
            if(current.right != null) q.add(current.right);
        }

        ListNode<Integer> current = head;
        int LNSum = 0;

        while(current != null)
        {
            LNSum += current.data;
            current = current.next;
        }


        return BSTSum == LNSum;
    }

    /**
     * Returns the sum of all the vertices in a graph that are reachable from a given
     * starting vertex.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the starting vertex
     * @return the sum of all the vertices
     */
    public static int graphSum(Vertex<Integer> start) {

        if(start == null) return 0;
        Set<Vertex<Integer>> visited = new HashSet<>();
        return dfs(start, visited);
    }


    public static int dfs(Vertex<Integer> start, Set<Vertex<Integer>> visited)
    {
        if(visited.contains(start)) return 0;
        visited.add(start);

        int sum = start.data;

        for(Vertex<Integer> neighbor: start.neighbors)
        {
            sum += dfs(neighbor, visited);
        }
        return sum;
    }

    /**
     * Returns the count of vertices in a graph that have an outdegree of 0.
     * 
     * Returns 0 if the starting vertex is null.
     * 
     * @param start the entrypoint to the graph
     * @return the count of vertices with outdegree 0
     */
    public static int sinkCount(Vertex<Integer> start) {
        if(start == null) return 0;
        Set<Vertex<Integer>> visited = new HashSet<>();
        return sinkCountDFS(start, visited);
    }

    public static int sinkCountDFS(Vertex<Integer> start, Set<Vertex<Integer>> visited)
    {
        if(visited.contains(start)) return 0;
        visited.add(start);

        

        return 0;
    }
}