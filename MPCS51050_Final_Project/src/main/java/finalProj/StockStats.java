package finalProj;

public class StockStats extends Component{

	private int numShares;
	private double bidMean;
	private double bidVariance;
	private double bidStandardDev;
	private double askMean;
	private double askVariance;
	private double askStandardDev;
	

	public StockStats(String stockName, int numShare) {
		this.name = stockName;
		this.numShares = numShare;
	}
	
	public double getBidMean() {
		return bidMean;
	}
	public void setBidMean(double bidMean) {
		this.bidMean = bidMean;
	}
	public double getBidStandardDev() {
		return bidStandardDev;
	}
	public void setBidStandardDev(double bidStandardDev) {
		this.bidStandardDev = bidStandardDev;
	}
	public double getBidVariance() {
		return bidVariance;
	}
	public void setBidVariance(double bidVariance) {
		this.bidVariance = bidVariance;
	}
	public double getAskMean() {
		return askMean;
	}
	public void setAskMean(double askMean) {
		this.askMean = askMean;
	}
	public double getAskVariance() {
		return askVariance;
	}
	public void setAskVariance(double askVariance) {
		this.askVariance = askVariance;
	}
	public double getAskStandardDev() {
		return askStandardDev;
	}
	public void setAskStandardDev(double askStandardDev) {
		this.askStandardDev = askStandardDev;
	}
	public int getNumShares() {
		return numShares;
	}
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
	@Override
	public void add(Component comp) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void readMsg(String msgBody) {
		// TODO Auto-generated method stub
		String[] msgFields = msgBody.split("\t");
		
		bidMean = Double.valueOf(msgFields[1].replaceAll("[^\\d.]", ""));
		bidVariance = Double.valueOf(msgFields[2].replaceAll("[^\\d.]", ""));
		bidStandardDev = Double.valueOf(msgFields[3].replaceAll("[^\\d.]", ""));
		askMean = Double.valueOf(msgFields[4].replaceAll("[^\\d.]", ""));
		askVariance = Double.valueOf(msgFields[5].replaceAll("[^\\d.]", ""));
		askStandardDev = Double.valueOf(msgFields[6].replaceAll("[^\\d.]", ""));	
	}
}
