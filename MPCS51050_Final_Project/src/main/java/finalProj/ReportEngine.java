package finalProj;

import java.text.DecimalFormat;

class ReportEngine 
{
	private static ReportEngine instance;

	private ReportEngine() 
	{}

	public static ReportEngine getInstance() 
	{
		if (instance == null) 
		{
			instance = new ReportEngine();	           
		} 
		
		return instance;
	}

	
	public String report(TradingEngine tradingEngine)
	{			
		DecimalFormat df = new DecimalFormat("###########.#######");
			
		StringBuilder reportMsg = new StringBuilder().append(tradingEngine.getReportMsgHeader());
		
		for (int i = 0; i < tradingEngine.getStrategies().length; i++)
		{
			tradingEngine.getPortfolio().setStrategy((tradingEngine.getStrategies())[i]);
			reportMsg.append(tradingEngine.getPortfolio().getStrategy().getStratName() + ": " + df.format(tradingEngine.getPortfolio().getStatistic()) + "|");
		}
		reportMsg.append("\n");
		
		System.out.println(reportMsg);
		
		return  reportMsg.toString();
		
	}
}
