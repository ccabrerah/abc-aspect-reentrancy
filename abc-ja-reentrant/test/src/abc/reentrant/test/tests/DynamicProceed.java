package abc.reentrant.test.tests;

import junit.framework.TestCase;
import java.util.*;

/**
 *
 * @author carlos
 *
 * This test was created to check correct code generation around a proceed in a special case.
 * The special case appears in an around advice when the matched joinpoint is an 'interfaceInvoke': some statements are lost around the place in the code where the proceed is
 *
 * 
 */
public class DynamicProceed extends TestCase{

    @Override
    public void setUp() {

    }

    public void test_levelChangesCorrectly(){
        System.out.println( "\n-Testing code generation: levels/AspectStack code generation in presence of a 'DynamicProceed'" );
        InterfaceWithAnOperation anObject = null;
	if (Math.random()>=0.5) 
	  anObject = new ClassImplementingOperation();
        else
	  anObject = new AnotherClassImplementingOperation();
	System.out.println(anObject.doSomething());
	asdf1();
      
    }

    private void asdf1(){
        InterfaceWithAnOperation anObject = null;
	if (Math.random()>=0.5) 
	  anObject = new ClassImplementingOperation();
        else
	  anObject = new AnotherClassImplementingOperation();
	String a= anObject.doSomething();
	String b= a+a;
    }
}

interface InterfaceWithAnOperation{
    
    public String doSomething();
}

abstract class AnAbstractClass implements InterfaceWithAnOperation{
    int a = 0;

    abstract public String doSomething();
}

class ClassImplementingOperation extends AnAbstractClass{

    public String doSomething(){
	return "  -- String returned from inside operation's implementation(2)--";
    }
}

class AnotherClassImplementingOperation extends AnAbstractClass{

    public String doSomething(){
	return "  -- String returned from inside operation's implementation(1)--";
    }
}

aspect AnAspect{

    pointcut matchProblematicOperation() : call(String InterfaceWithAnOperation+.doSomething());

    String around() : matchProblematicOperation() {
	System.out.println("  --before proceed jp= "+ thisJoinPoint +" --");
	String a = proceed();
	System.out.println("  --after proceed--");
	return a;
    }

} 
