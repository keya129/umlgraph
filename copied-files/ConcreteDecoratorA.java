/** 
* @opt edgecolor"yellow"
* @opt nodefontname "Times"
* @opt bgcolor ".7 .9 1"
* @opt nodefillcolor "#a0a0a0"
* @opt nodefontsize 14
 * @opt operations
 * @opt all
 * @opt attributes
 * @opt types
 * @hidden
 */
class UMLOptions {}
/**
 * @depend - <uses> - Component
 */

public class ConcreteDecoratorA extends Decorator {

	private String addedState;

    public ConcreteDecoratorA( Component c)
    {
        super( c ) ;
    }

    public String operation()
    {
        addedState = super.operation() ;
        return addedBehavior( addedState ) ;
    }

	private String addedBehavior(String in) {
		return "<em>" + addedState + "</em>" ;
	}

}
