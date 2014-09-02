package finalProj;

public class Tick extends Component {

	private double bidPrice;
	private int bidQuantity;
	private double askPrice;
	private int askQuantity;
	
	public Tick(String stockName, double bidPrice, int bidQuantity, double askPrice, int askQuantity)
	{
		super(stockName);
		this.setBidPrice(bidPrice);
		this.setBidQuantity(bidQuantity);
		this.setAskPrice(askPrice);
		this.setAskQuantity(askQuantity);
	}

	@Override
	public void add(Component comp) {
		// TODO Auto-generated method stub
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(int bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}

	public int getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(int askQuantity) {
		this.askQuantity = askQuantity;
	}

	@Override
	public void readMsg(String body) {
		// TODO Auto-generated method stub
		
	}

}
