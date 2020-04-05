package Graph;

import java.util.ArrayList;

// 图的深度优先遍历
// 时间复杂度：O(V+E)
public class GraphDFS {
    // 定义自己的图对象
    private Graph G;
    // 记录是否有过遍历
    private boolean[] visited;
    // 先序遍历后的结果传入order中
    private ArrayList<Integer> order = new ArrayList<>();
    // 保存后续遍历的结果
    private ArrayList<Integer> post = new ArrayList<>();

    // 构造函数完成深度优先遍历
    public GraphDFS(Graph G){
        // 获取用户传来的图
        this.G = G;
        // 定义visited的空间，图中每一个顶点对应的值
        visited = new boolean[G.V()];
        // 开始深度优先遍历
        for (int i=0;i<G.V();i++){
            // 每个没有遍历过的节点
            if (!visited[i]){
                // 进行DFS遍历
                dfs(i);
            }
        }
    }

    // 私有方法对传入的v进行递归深度优先遍历
    private void dfs(int v){
        // 将当前顶点完成访问记录
        visited[v] = true;
        // 将当前顶点放出遍历的结果中
        order.add(v);
        // 遍历当前节点的相邻节点
        for (int w: G.adj(v)){
            // 如果该节点没有被访问过的话
            if (!visited[w]){
                // 递归调用该节点
                dfs(w);
            }
        }
        // 后序遍历
        post.add(v);
    }

    // 返回先序深度优先遍历后的结果
    public Iterable<Integer> getOrder(){
        return order;
    }

    // 返回后序深度优先遍历后的结果
    public Iterable<Integer> getPost(){
        return post;
    }

    // 测试函数
    public static void main(String[] args) {
        Graph g = new Graph("./src/Graph/g2.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println("先序遍历：" + graphDFS.getOrder());
        System.out.println("后序遍历：" + graphDFS.getPost());
    }
}
