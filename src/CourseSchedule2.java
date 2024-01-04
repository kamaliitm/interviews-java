import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CourseGraph2 {
    Map<Integer, List<Integer>> edges;

    CourseGraph2() {
        edges = new HashMap<>();
    }

    void addEdge(int fromVertex, int toVertex) {
        List<Integer> edgesFromVertex = edges.getOrDefault(fromVertex, new ArrayList<>());
        edgesFromVertex.add(toVertex);
        edges.put(fromVertex, edgesFromVertex);
    }
}

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        CourseGraph2 graph = new CourseGraph2();
        for (int[] edge: prerequisites) {
            graph.addEdge(edge[0], edge[1]);
        }

        List<Integer> orderedCourses = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] recursionCourses = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!updateCoursesArrDFS(graph, visited, recursionCourses, i, orderedCourses)) {
                    return new int[]{};
                };
            }
        }

        return orderedCourses.stream().mapToInt(Integer::intValue).toArray() ;
    }

    private boolean updateCoursesArrDFS(CourseGraph2 graph, boolean[] visited, boolean[] recursionCourses, int currCourse,
                                     List<Integer> orderedCourses) {
        if (visited[currCourse]) return true;

        recursionCourses[currCourse] = true;
        if (graph.edges.containsKey(currCourse)) {
            for (int dependentCourse: graph.edges.get(currCourse)) {
                if (recursionCourses[dependentCourse]) { // already seen the course, meaning there's a cycle
                    return false;
                }

                if (!updateCoursesArrDFS(graph, visited, recursionCourses, dependentCourse, orderedCourses)) {
                    return false;
                }
            }
        }

        recursionCourses[currCourse] = false;
        visited[currCourse] = true;
        orderedCourses.add(currCourse);

        return true;
    }


}
