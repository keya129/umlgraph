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
 * @assoc 1 component 1 Component
 * @depend - <uses> - Component
 */

public class Decorator implements Component {


    public Decorator( Component c )
    {
        component = c ;
    }

    public String operation()
    {
        return component.operation() ;
    }

}
