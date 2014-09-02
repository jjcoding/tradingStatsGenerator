package finalProj;

import java.util.LinkedList;

public abstract class Composite extends Component
{	
	LinkedList<Component> list = new LinkedList<Component>();
	
	public Composite(String name)
	{
		super(name);
	}
	
	public Composite()
	{
		super();
	}

	@Override
	public void add(Component comp) {
		list.add(comp);		
	}

}
