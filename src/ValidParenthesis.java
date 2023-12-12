import java.util.Stack;

public class ValidParenthesis {

    public boolean isValid(String s) {
        Stack<Character> parenStack = new Stack<>();

        for (int i=0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '}':
                    if (parenStack.isEmpty() || parenStack.peek() != '{') {
                        return false;
                    }
                    parenStack.pop();
                    break;
                case ']':
                    if (parenStack.isEmpty() ||parenStack.peek() != '[') {
                        return false;
                    }
                    parenStack.pop();
                    break;
                case ')':
                    if (parenStack.isEmpty() ||parenStack.peek() != '(') {
                        return false;
                    }
                    parenStack.pop();
                    break;
                default:
                    parenStack.push(ch);
                    break;
            }
        }

        return parenStack.empty();
    }

    public static void main(String[] args) {
        ValidParenthesis validParenthesis = new ValidParenthesis();
        boolean isValid = validParenthesis.isValid("]");
        System.out.println(isValid);
    }
}
