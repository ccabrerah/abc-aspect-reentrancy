package abc.reentrant.test.tests;

import junit.framework.TestCase;


/**
 *
 * @author carlos
 *
 * TODO: esto sólo funciona con singleton aspects, falta agregar el código para los otros casos.
 *      Esto es porque que aspectOf() espera un parámetro en tales casos, el cual usa para entregar la instancia adecuada
 */
public class AspectStackUpdating extends TestCase{

    public void test_aspectStack(){
        System.out.println("\n-Testing AspectStack correct updating");

        doSomething();
    }

    private void doSomething() {
    }

    static public void assertTopElementIs(Object reference, String message){
        Object currentTopElement = abc.reentrant.runtime.AspectStack.peek();

        System.out.println(message+" top element in the aspect stack is: " + currentTopElement +" expected: "+reference);

        assertEquals(reference, currentTopElement);

    }

    static public void assertTopElementIsNot(Object reference, String message){
        Object currentTopElement = abc.reentrant.runtime.AspectStack.peek();

        System.out.println(message+" asserting top element is not equal to: "+reference+" actual top element is: "+currentTopElement);

        assertNotSame( currentTopElement, reference );
    }

    static public boolean returnTrue(){
        return true;
    }

}

aspect BasicReentrancyAdvice{

    pointcut intercept_doSomething() : call( void AspectStackUpdating.doSomething() ) ;
    pointcut trivial_if() : if( true );
    pointcut static_call_if() : if( AspectStackUpdating.returnTrue() );
    
    void around() : intercept_doSomething() && trivial_if() && static_call_if() {
        AspectStackUpdating.assertTopElementIs( this, "before proceed" );
        proceed();
        AspectStackUpdating.assertTopElementIs( this, "after proceed" );

    }

}
