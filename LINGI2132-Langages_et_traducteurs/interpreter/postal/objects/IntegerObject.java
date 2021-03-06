
package postal.objects;

import postal.classes.*;

/*
 * represents an integer
 */
public class IntegerObject extends PostalObject
{
    int value; 
    public IntegerObject(int v)
    {
    	super(new IntegerClass());
        this.value = v;
    }
    
	public int value() {
		return value;
	}
	

	public String toString()
	{
		String s="";
    	s+="[(Integer Object) : ";
    	s+= value;
    	s+="]";
    	return s;
	}
    
}
