package finalProj;

public class Stock extends Composite
{
	double sum_bid_price_times_quantity;
	double sum_ask_price_times_quantity;
	double sum_bid_price_square_times_quantity;
	double sum_ask_price_square_times_quantity;
	int sum_bidQuantity;
	int sum_askQuantity;
	
	public Stock()
	{
		super();
	}
	
	public Stock(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}


	@Override
	public void readMsg(String msgBody) {
		// TODO Auto-generated method stub
		String[] msgFields = msgBody.split("\t");
		
		double bidPrice = Double.valueOf(msgFields[1].replaceAll("[^\\d.]", ""));
		int bidQuantity = Integer.valueOf(msgFields[2].replaceAll("[^\\d.]", ""));
		double askPrice = Double.valueOf(msgFields[3].replaceAll("[^\\d.]", ""));
		int askQuantity = Integer.valueOf(msgFields[4].replaceAll("[^\\d.]", ""));
		
		this.add(new Tick(this.name, bidPrice, bidQuantity, askPrice, askQuantity));
		
		sum_bid_price_times_quantity += bidPrice * bidQuantity;
		sum_ask_price_times_quantity += askPrice * askQuantity;
		sum_bid_price_square_times_quantity += bidPrice * bidPrice * bidQuantity;
		sum_ask_price_square_times_quantity += askPrice * askPrice * askQuantity;;
		sum_bidQuantity += bidQuantity;
		sum_askQuantity += askQuantity;
	}
}
