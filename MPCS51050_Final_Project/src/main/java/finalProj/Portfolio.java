package finalProj;

public class Portfolio extends Composite {
	
	public void readMsg(String statMsg)
	{
		String[] msgFields = statMsg.split("\t");
		
		MyIterator itr = new MyIterator(this);
		StockStats current;
		while (itr.hasNext()) 
		{	
			current = (StockStats)itr.next();
				
			if (current.getName().equals(msgFields[0]))
			{				
				current.setBidMean(Double.valueOf(msgFields[1]));
				current.setBidVariance(Double.valueOf(msgFields[2]));
				current.setBidStandardDev(Double.valueOf(msgFields[3]));
				current.setAskMean(Double.valueOf(msgFields[4]));
				current.setAskVariance(Double.valueOf(msgFields[5]));
				current.setAskStandardDev(Double.valueOf(msgFields[6]));
		
			}
		}
	}
}
