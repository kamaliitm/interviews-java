import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class CourseGraph {
    int numCourses;
    Map<Integer, Set<Integer>> edges;

    CourseGraph(int numCourses) {
        this.numCourses = numCourses;
        this.edges = new HashMap<>();
    }

    void addDirectedEdge(int v1, int v2) {
        Set<Integer> edgesForV1 = edges.getOrDefault(v1, new HashSet<>());
        edgesForV1.add(v2);
        edges.put(v1, edgesForV1);
    }

    boolean hasCycle() {

        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            boolean hasCycleForVertex = hasCycleHelper(visited, recursionStack, i);
            if (hasCycleForVertex) {
                return true;
            }
        }

        return false;
    }

    boolean hasCycleHelper(boolean[] visited, boolean[] recursionStack, int currCourse) {
        if (visited[currCourse]) return false;

        recursionStack[currCourse] = true;
        visited[currCourse] = true;
        if (edges.containsKey(currCourse)) {
            Set<Integer> edgesForCurrCourse = edges.get(currCourse);
            for (Integer dependentCourse: edgesForCurrCourse) {
                if (recursionStack[dependentCourse]) {
                    return true;
                }

                if (!visited[dependentCourse]) {
                    boolean hasCycle = hasCycleHelper(visited, recursionStack, dependentCourse);
                    if (hasCycle) {
                        return true;
                    }

                }
            }
        }

        recursionStack[currCourse] = false;

        return false;
    }

}

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        CourseGraph graph = new CourseGraph(numCourses);

        for (int[] edge: prerequisites) {
            graph.addDirectedEdge(edge[0], edge[1]);
        }

        return !graph.hasCycle();
    }
}
