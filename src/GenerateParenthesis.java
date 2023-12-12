import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> parens = new ArrayList<>();
        generateParenthesisRecursive(n, n, "", parens);
        return parens;
    }

    private void generateParenthesisRecursive(int remainingLeft, int remainingRight, String currString,
                                              List<String> parens) {
        if (remainingLeft == 0 && remainingRight == 0) {
            parens.add(currString);
            return;
        }

        if (remainingLeft > 0) {
            generateParenthesisRecursive(remainingLeft-1, remainingRight, currString+"(", parens);
        }

        if (remainingRight > remainingLeft) {
            generateParenthesisRecursive(remainingLeft, remainingRight-1, currString+")", parens);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> parens = generateParenthesis.generateParenthesis(2);
        System.out.println(parens);

        List<String> parens2 = generateParenthesis.generateParenthesis(3);
        System.out.println(parens);
    }
}
