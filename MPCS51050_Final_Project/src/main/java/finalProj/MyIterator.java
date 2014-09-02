package finalProj;

public class MyIterator {

	Composite composite;
	int curPosition;
	
	public MyIterator(Composite composite){
		this.composite = composite;
		this.curPosition = -1;
	}
	
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		if (composite != null)
		{
			return (curPosition + 1) < composite.list.size();
		}
		return false;
	}

	public Component next() {
		// TODO Auto-generated method stub
		return composite.list.get(++curPosition);
	}
}
