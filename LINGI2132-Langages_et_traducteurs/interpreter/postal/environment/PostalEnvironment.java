package postal.environment;

import java.util.Hashtable;

import postal.classes.PostalClass;
import postal.objects.PostalObject;
import postal.objects.StdioObject;

/*
 * Represents the environment to execute postal nodes
 */
public class PostalEnvironment {
	
	//variables
	Hashtable<String,PostalObject> vc;
	//classes
	Hashtable<String,PostalClass> cc;
	
	/*
	 * build a new environment
	 */
	public PostalEnvironment()
	{
		vc = new Hashtable<String,PostalObject>();
		cc = new Hashtable<String,PostalClass>();
		
		//add a stdio object to the environment to send messages to print
		setVariable("stdio", new StdioObject()); 
	}

	public PostalObject getVariable(String identifier) 
	{
		return vc.get(identifier);
	}
	
	public void setVariable(String identifier, PostalObject element) 
	{
		vc.put(identifier, element);
	}
	
	public PostalObject newObject(String className, PostalObject newMessage)
	{
		return cc.get(className).messageReceived((PostalObject)null, newMessage);
	}

	public void insertClass(PostalClass c) {
		cc.put(c.getName(), c);
	}
	
	public PostalClass getClass(String cn) {
		return cc.get(cn);
	}

}
