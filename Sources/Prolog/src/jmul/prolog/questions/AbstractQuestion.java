package jmul.prolog.questions;


/**
 * A base class for questions.
 *
 * @author Kristian Kutin
 */
abstract class AbstractQuestion implements Question {

    /**
     * A function symbol.
     */
    public final String functionSymbol;

    /**
     * Creates a new question according to the specified parameter.
     *
     * @param functionSymbol
     *        a function symbol
     */
    public AbstractQuestion(String functionSymbol) {

        super();

        if (functionSymbol == null) {

            throw new IllegalArgumentException("No function symbol (null) was specified!");
        }

        if (functionSymbol.trim().isEmpty()) {

            throw new IllegalArgumentException("No function symbol (empty string) was specified!");
        }

        this.functionSymbol = functionSymbol;
    }

}
