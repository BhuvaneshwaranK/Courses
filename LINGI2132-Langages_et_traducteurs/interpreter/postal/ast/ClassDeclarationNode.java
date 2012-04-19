package postal.ast;

import java.util.Hashtable;
import java.util.LinkedList;

import postal.classes.UserDefinedClass;
import postal.environment.MessageImplementation;
import postal.environment.PostalEnvironment;

/*
 * represents a class definition
 */
public class ClassDeclarationNode extends PostalNode {

	private String className;
	Hashtable<String,MessageImplementation> messagesImplementations;
	LinkedList<String> attributes;
	private String superClassName;
	
	
	/*
	 * create node with a class name and a super class name
	 */
	public ClassDeclarationNode(String className, String superClassName) {
		this.className = className;
		this.superClassName = superClassName;
		this.messagesImplementations = new Hashtable<String,MessageImplementation>();
		this.attributes = new LinkedList<String>();
	}
	/*
	 * declare a class variable
	 */	
	public void addArgument(String argumentName)
	{
		attributes.add(argumentName);
	}
	
	/*
	 * declare a class message implementation
	 */
	public void addMessageImplementation(MessageImplementation mi)
	{
		
		messagesImplementations.put(mi.getName(), mi);
	}
	
	/*
	 * Effectively adds the class to environment e
	 */
	public ElementNode execute(PostalEnvironment e) {
		UserDefinedClass c = new UserDefinedClass(className, messagesImplementations, attributes,e.getClass(superClassName));
		e.insertClass(c);
		return null;
	}

}