package com.dajce_olti.calculator;

public class AritmeticTokenizer {

    private int currentPosition;
    private String expression;

    public AritmeticTokenizer(String expression) {
        this.expression = expression;
        currentPosition = 0;
    }

    /**
     * Return the next sequence of numbers and dots or the next char
     * @return String or null if no tokens left
     */
    public String nextToken() {

        StringBuilder result = new StringBuilder();
        char currentChar;
        boolean finish = false;

        while (!finish && currentPosition < this.expression.length()) {

            currentChar = expression.charAt(currentPosition);

            if (currentChar >= 48 && currentChar <= 57 || currentChar == '.') {
                result.append(currentChar);
                this.currentPosition += 1;
            } else {
                if (result.length() == 0) {
                    result.append(currentChar);
                    this.currentPosition += 1;
                    finish = true;
                }else{
                    finish = true;
                }
            }
        }
        if(result.length() != 0)
            return result.toString();
        else
            return null;
    }

    public void resetPosition(){
        this.currentPosition = 0;
    }
}
