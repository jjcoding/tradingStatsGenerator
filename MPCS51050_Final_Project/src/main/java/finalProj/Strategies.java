package finalProj;

class AskMean extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return stock.sum_ask_price_times_quantity / stock.sum_askQuantity;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Mean";
	}
}


class AskVariance extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return stock.sum_ask_price_square_times_quantity / stock.sum_askQuantity 
				- Math.pow(stock.sum_ask_price_times_quantity / stock.sum_askQuantity, 2);
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Var";
	}

}

class AskStandardDev extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return Math.sqrt(stock.sum_ask_price_square_times_quantity / stock.sum_askQuantity 
				- Math.pow(stock.sum_ask_price_times_quantity / stock.sum_askQuantity, 2));
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Std";
	}

}

class BidMean extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return stock.sum_bid_price_times_quantity / stock.sum_bidQuantity;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Mean";
	}

}


class BidVariance extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return stock.sum_bid_price_square_times_quantity / stock.sum_bidQuantity - 
				Math.pow(stock.sum_bid_price_times_quantity / stock.sum_bidQuantity, 2);
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Var";
	}

}

class BidStandardDev extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Stock stock = (Stock) comp;
		return Math.sqrt(stock.sum_bid_price_square_times_quantity / stock.sum_bidQuantity - 
				Math.pow(stock.sum_bid_price_times_quantity / stock.sum_bidQuantity, 2));
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Std";
	}

}

class PortfolioAskMean extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Portfolio portfolio = (Portfolio) comp;

		double portfolioAskMean = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioAskMean += current.getAskMean() * current.getNumShares();
		}

		return portfolioAskMean;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Mean";
	}

}


class PortfolioAskVariance extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Portfolio portfolio = (Portfolio) comp;

		double portfolioAskStandardDev = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioAskStandardDev += current.getAskVariance() * Math.pow(current.getNumShares(), 2);
		}

		return portfolioAskStandardDev;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Var";
	}

}

class PortfolioAskStandardDev extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Portfolio portfolio = (Portfolio) comp;

		double portfolioAskStandardDev = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioAskStandardDev += current.getAskVariance() * Math.pow(current.getNumShares(), 2);
		}

		return Math.sqrt(portfolioAskStandardDev);
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Ask Std";
	}

}

class PortfolioBidMean extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Portfolio portfolio = (Portfolio) comp;

		double portfolioBidMean = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioBidMean += current.getBidMean() * current.getNumShares();
		}

		return portfolioBidMean;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Mean";
	}

}

class PortfolioBidVariance extends StatStrategy {


	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub
		Portfolio portfolio = (Portfolio) comp;

		double portfolioBidVariance = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioBidVariance += current.getAskVariance() * Math.pow(current.getNumShares(), 2);
		}

		return portfolioBidVariance;
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Var";
	}

}

class PortfolioBidStandardDev extends StatStrategy {

	@Override
	public double getStatistic(Component comp) {
		// TODO Auto-generated method stub

		Portfolio portfolio = (Portfolio) comp;
		double portfolioBidStandardDev = 0;

		MyIterator itr = new MyIterator(portfolio);

		for (StockStats current = (StockStats) portfolio.list.getFirst();  itr.hasNext(); current = (StockStats)itr.next())
		{	
			portfolioBidStandardDev += current.getAskVariance() * Math.pow(current.getNumShares(), 2);
		}

		return Math.sqrt(portfolioBidStandardDev);
	}

	@Override
	public String getStratName() {
		// TODO Auto-generated method stub
		return "Bid Std";
	}

}

