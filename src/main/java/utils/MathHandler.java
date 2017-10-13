/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.udojava.evalex.Expression;

/**
 * Handles Math operations coming from Expression Utility Class
 */
public class MathHandler {

    /**
     * Represents default message of invalid expressions
     */
    private static final String INVALID_EXPRESSION = "Expression is invalid!";

    /**
     * A message header!
     */
    private static final String MESSAGE_HEADER =
            "That looks easy! :nerd: \n";

    /**
     * Handles Expression Eval Math Operations
     *
     * @param expression String with the expression that the user typed
     * @return A String with the expression result or the error occured
     */
    public static String handleOperation(String expression) {
        try {
            return MESSAGE_HEADER + expression + "="
                    + new Expression(expression).eval().toEngineeringString();
        } catch (Expression.ExpressionException e) {
            return INVALID_EXPRESSION + "\nExpression:" + expression;
        } catch (NumberFormatException e) {
            return INVALID_EXPRESSION + "\nThe expression does not have"
                    + " valid numbers!\nExpression:" + expression;
        }
    }

    /**
     * Hides default constructor
     */
    private MathHandler() { };
}
