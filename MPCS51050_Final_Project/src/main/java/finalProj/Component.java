package finalProj;

abstract class Component {
	
	String name;

	private StatStrategy strat; 

	public Component(String name) {
		this.name = name;
	}
	
	public Component() {
		this.name = "DefaultName";
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setStrategy(StatStrategy strat) 
	{
		this.strat = strat;	
	}
	
	public double getStatistic()
	{
		return this.strat.getStatistic(this);
	}
	
	public StatStrategy getStrategy() 
	{
		return this.strat;	
	}
	
	public abstract void add(Component comp);

	public abstract void readMsg(String body);

}
