package finalProj;

public class TradingEngine{
	
	
	private Portfolio portfolio;
	private StatStrategy[] strategies;
	private StringBuilder reportMsgHeader;
	private String location;
	private ReportEngine reportEngine;
	
	public TradingEngine(String location, Portfolio portfolio, StatStrategy[] strategies)
	{
		this.setLocation(location);
		this.portfolio = portfolio;
		this.setStrategies(strategies);
		
		reportMsgHeader = new StringBuilder();
		reportMsgHeader.append(location + " Portfolio: ");
		
		MyIterator itr = new MyIterator(portfolio);
		StockStats current;
		while (itr.hasNext())
		{	
			current = (StockStats)itr.next();
			reportMsgHeader.append(current.getName() + " ("+ current.getNumShares() + " shares)  ");	
		}
		
		reportMsgHeader.append("\n|");
		
		//System.out.println(reportMsgHeader);
	}
	
	public String report()
	{
		reportEngine = ReportEngine.getInstance();
		return reportEngine.report(this);
	}


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Portfolio getPortfolio()
	{
		return this.portfolio;
	}
	
	public StatStrategy[] getStrategies() {
		return strategies;
	}


	public void setStrategies(StatStrategy[] strategies) {
		this.strategies = strategies;
	}
	

	public StringBuilder getReportMsgHeader() {
		return this.reportMsgHeader;
	}

}
