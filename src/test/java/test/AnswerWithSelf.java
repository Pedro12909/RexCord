package test;

import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *  Returns the mock object itself for any method that returns the specified class.
 *  @see <a href="http://jakegoulding.com/blog/2012/01/09/stubbing-builder-pattern-in-mockito/">Stubbing the Builder pattern in Mockito</a>
 */
public class AnswerWithSelf implements Answer<Object> {

    private final Answer<Object> delegate = new ReturnsEmptyValues();
    private final Class<?> clazz;

    public AnswerWithSelf(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object answer(InvocationOnMock invocation) throws Throwable {
        return (clazz == invocation.getMethod().getReturnType())
                ? invocation.getMock()
                : delegate.answer(invocation);
    }
}
