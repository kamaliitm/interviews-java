import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Rectangle {
    int startIndex;
    int height;

    Rectangle(int startIndex, int height) {
        this.startIndex = startIndex;
        this.height = height;
    }
}

public class LargestRectangle {

    public static long largestRectangle(List<Integer> h) {

        Stack<Rectangle> stack = new Stack<>();
        int noOfRectangles = h.size();

        long maxArea = 0L;

        for (int i = 0; i < noOfRectangles; i++) {
            int currHeight = h.get(i);
            Rectangle currRectangle = new Rectangle(i, currHeight);

            if (stack.empty()) {
                stack.push(currRectangle);
                continue;
            }

            int leftStartIndex = i;
            while (!stack.empty() && stack.peek().height > currHeight) {
                Rectangle rectangleAtTop = stack.pop();
                maxArea = Math.max(maxArea, ((long) rectangleAtTop.height) * (i - rectangleAtTop.startIndex));
                leftStartIndex = rectangleAtTop.startIndex;
            }

            currRectangle.startIndex = leftStartIndex;
            stack.push(currRectangle);
        }

        while (!stack.empty()) {
            Rectangle rectangle = stack.pop();
            maxArea = Math.max(maxArea, ((long) rectangle.height) * (noOfRectangles - rectangle.startIndex));
        }

        return maxArea;
    }

    // rewriting the same above function for practice
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Rectangle> incrementalHeightStack = new Stack<>();
        int maxArea = 0;

        for (int i=0; i < heights.length; i++) {
            if (incrementalHeightStack.isEmpty()) {
                incrementalHeightStack.add(new Rectangle(i, heights[i]));
                continue;
            }

            int startIndex = i;
            while (!incrementalHeightStack.isEmpty() && incrementalHeightStack.peek().height > heights[i]) {
                Rectangle rect = incrementalHeightStack.pop();
                maxArea = Math.max(maxArea, rect.height * (i-rect.startIndex));
                startIndex = rect.startIndex;
            }

            incrementalHeightStack.add(new Rectangle(startIndex, heights[i]));
        }

        while (!incrementalHeightStack.isEmpty()) {
            Rectangle rect = incrementalHeightStack.pop();
            maxArea = Math.max(maxArea, rect.height * (heights.length - rect.startIndex));
        }

        return maxArea;
    }

    public static void main(String []args) {
//        long maxArea = largestRectangle(Arrays.asList(1, 2, 3, 4, 5));
        long maxArea = largestRectangle(Arrays.asList(2, 1, 5, 6, 2, 3));
        System.out.println(maxArea);
    }


}
