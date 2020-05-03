package com.dajce_olti.calculator;

import java.util.Stack;

public class Evaluator {

    private String expression;
    private Stack<Float> values_stack;
    private Stack<Character> operators_stack;


    public Evaluator(){
        values_stack = new Stack<>();
        operators_stack = new Stack<>();
    }

    /**
     * @param expression expression tu evaluate
     * @return value calculated
     */
    public float evaluate(String expression) throws NumberFormatException, InvalidOperationException{
        AritmeticTokenizer atok = new AritmeticTokenizer(expression);
        String currentToken;

        while ((currentToken = atok.nextToken()) != null) {

            //numbers have to be always pushed in the stack
            if(currentToken.length() > 1 || (currentToken.charAt(0) >= 48 && currentToken.charAt(0) <= 57)){
                values_stack.push(Float.parseFloat(currentToken));
            } else {

                //check if valid operator
                if(isValidOperator(currentToken.charAt(0))){
                    char operator = currentToken.charAt(0);

                    if(operators_stack.empty() || operator == '(' || operators_stack.lastElement() == '('){
                        operators_stack.push(operator);
                    } else if(!operators_stack.empty() && operator == ')'){
                        while(operators_stack.lastElement() != '('){
                            values_stack.push(execute(operators_stack.pop()));
                        }
                        //remove the '(' operator
                        operators_stack.pop();
                    } else {
                        switch(operator){
                            case '*':
                            case '/':
                                operators_stack.push(operator);break;
                            case '+':
                            case '-': {
                                while(operators_stack.lastElement() == '*' || operators_stack.lastElement() == '/'){
                                    values_stack.push(execute(operators_stack.pop()));
                                }
                                if(operator == '+' && operators_stack.lastElement() == '-'){
                                    values_stack.push(execute(operators_stack.pop()));
                                }
                                operators_stack.push(operator);
                            }
                        }
                    }
                } else {
                    throw new InvalidOperationException();
                }
            }
        }

        while(values_stack.size() != 1){
            values_stack.push(execute(operators_stack.pop()));
        }
        return values_stack.pop();
    }

    /**
     * Check if @operator is a valid operator
     * @param operator operator to evaluate
     * @return boolean
     */
    private boolean isValidOperator(char operator){
        switch(operator){
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                return true;
            default:
                return false;
        }
    }

    private float execute(char operator){
        float val2 = values_stack.pop();
        float val1 = values_stack.pop();

        switch(operator){
            case '+': return val1 + val2;
            case '-': return val1 - val2;
            case '/': return val1 / val2;
            case '*': return val1 * val2;
        }
        return Float.MIN_VALUE;
    }

    public static Evaluator createEvaluator(){
        return new Evaluator();
    }
}
