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

    public static void main(String []args) {
//        long maxArea = largestRectangle(Arrays.asList(1, 2, 3, 4, 5));
        long maxArea = largestRectangle(Arrays.asList(2, 1, 5, 6, 2, 3));
        System.out.println(maxArea);
    }


}
