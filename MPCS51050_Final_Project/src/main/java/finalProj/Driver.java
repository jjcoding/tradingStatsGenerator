package finalProj;

import java.text.DecimalFormat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class Driver {

	public static void main(String args[]) throws Exception {

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		ConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory("tcp://localhost:61616");

		Portfolio portfolioTokyo = new Portfolio();
		Portfolio portfolioLondon = new Portfolio();
		Portfolio portfolioNewYork = new Portfolio();

		// portfolioTokyo holds 20 shares of MSFT and 30 shares of ORCL.
		portfolioTokyo.add(new StockStats("MSFT", 20));
		portfolioTokyo.add(new StockStats("ORCL", 30));

		portfolioLondon.add(new StockStats("MSFT", 50));

		portfolioNewYork.add(new StockStats("MSFT", 30));
		portfolioNewYork.add(new StockStats("ORCL", 20));
		portfolioNewYork.add(new StockStats("IBM", 10));

		// Tokyo Strategies includes Ask Mean, Ask Variance, and Ask Starndard Dev.
		StatStrategy[] stratsTokyo = {new PortfolioAskMean(), new PortfolioAskVariance(), new PortfolioAskStandardDev()};
		StatStrategy[] stratsLondon = {new PortfolioBidMean(), new PortfolioBidVariance(), new PortfolioBidStandardDev()};
		StatStrategy[] stratsNewYork = {new PortfolioAskMean(), new PortfolioBidMean()};

		TradingEngine tradingEngineTokyo = new TradingEngine("Tokyo", portfolioTokyo, stratsTokyo);
		TradingEngine tradingEngineLondon = new TradingEngine("London", portfolioLondon, stratsLondon);
		TradingEngine tradingEngineNewYork = new TradingEngine("New York", portfolioNewYork, stratsNewYork);

		CamelContext contextTokyo = new DefaultCamelContext();
		CamelContext contextLondon = new DefaultCamelContext();
		CamelContext contextNewYork = new DefaultCamelContext();

		// connect to ActiveMQ JMS broker listening on localhost on port 61616
		contextTokyo.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		contextLondon.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		contextNewYork.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		RouteBuilder tradingEngineChannelTokyo = new ToTradingEngineChannel(tradingEngineTokyo);
		RouteBuilder tradingEngineChannelLondon = new ToTradingEngineChannel(tradingEngineLondon);
		RouteBuilder tradingEngineChannelNewYork = new ToTradingEngineChannel(tradingEngineNewYork);

		// add our route to the CamelContext
		contextTokyo.addRoutes(tradingEngineChannelTokyo);
		contextLondon.addRoutes(tradingEngineChannelLondon);
		contextNewYork.addRoutes(tradingEngineChannelNewYork);




		// create CamelContext
		CamelContext contextCanonical = new DefaultCamelContext();
		contextCanonical.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		// add our route to the CamelContext
		//contextCanonical.addRoutes(new CanonicalDataModel(new TradingEngine[]{tradingEngineTokyo, tradingEngineLondon, tradingEngineNewYork}));


		// create CamelContext
		CamelContext contextLoadData = new DefaultCamelContext();
		contextLoadData.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		// add our route to the CamelContext
		contextLoadData.addRoutes(new StatGenerator());
		


		contextTokyo.start();
		contextLondon.start();
		contextNewYork.start();
		contextCanonical.start();
		Thread.sleep(10000);

		contextLoadData.start();
		Thread.sleep(200000);

		// stop the CamelContext
		contextTokyo.stop();
		contextLondon.stop();
		contextNewYork.stop();
		contextLoadData.stop();
		contextCanonical.stop();
	}
}


class StatGenerator extends RouteBuilder{

	Component MSFTstock = new Stock("MSFT");
	Component ORCLstock = new Stock("ORCL");
	Component IBMstock = new Stock("IBM");

	DecimalFormat mean_df = new DecimalFormat("###.##");
	DecimalFormat variance_df = new DecimalFormat("##.#######");

	public void configure() {
		//from("file:data/inbox?noop=true")
		from("jms:queue:MPCS_51050_Raw_Data")
		//.log("RETRIEVED:${file:name}")
		//.unmarshal().csv().split(body())
		//.log("RECEIVED:jms queue: ${body} from file: ${header.CamelFileNameOnly}")
		.choice()
		.when(body().regex(".*MSFT.*"))
		.process(new Processor(){
			public void process(Exchange e) throws Exception{
				MSFTstock.readMsg(e.getIn().getBody(String.class));

				StringBuilder msgBody = new StringBuilder();

				msgBody.append(MSFTstock.getName() + "\t");
				MSFTstock.setStrategy(new BidMean());
				msgBody.append(mean_df.format(MSFTstock.getStatistic()) + "\t");
				MSFTstock.setStrategy(new BidVariance());
				msgBody.append(variance_df.format(MSFTstock.getStatistic()) + "\t");
				MSFTstock.setStrategy(new BidStandardDev());
				msgBody.append(variance_df.format(MSFTstock.getStatistic()) + "\t");
				MSFTstock.setStrategy(new AskMean());
				msgBody.append(mean_df.format(MSFTstock.getStatistic()) + "\t");
				MSFTstock.setStrategy(new AskVariance());
				msgBody.append(variance_df.format(MSFTstock.getStatistic()) + "\t");
				MSFTstock.setStrategy(new AskStandardDev());
				msgBody.append(variance_df.format(MSFTstock.getStatistic()) + "\t");

				e.getIn().setBody(msgBody);
			}
		})
		.to("jms:topic:STATS_TOPIC_MSFT")
		.when(body().regex(".*ORCL.*"))
		.process(new Processor(){
			public void process(Exchange e) throws Exception{

				ORCLstock.readMsg(e.getIn().getBody(String.class));

				StringBuilder msgBody = new StringBuilder();

				msgBody.append(ORCLstock.getName() + "\t");
				ORCLstock.setStrategy(new BidMean());
				msgBody.append(mean_df.format(ORCLstock.getStatistic()) + "\t");
				ORCLstock.setStrategy(new BidVariance());
				msgBody.append(variance_df.format(ORCLstock.getStatistic()) + "\t");
				ORCLstock.setStrategy(new BidStandardDev());
				msgBody.append(variance_df.format(ORCLstock.getStatistic()) + "\t");
				ORCLstock.setStrategy(new AskMean());
				msgBody.append(mean_df.format(ORCLstock.getStatistic()) + "\t");
				ORCLstock.setStrategy(new AskVariance());
				msgBody.append(variance_df.format(ORCLstock.getStatistic()) + "\t");
				ORCLstock.setStrategy(new AskStandardDev());
				msgBody.append(variance_df.format(ORCLstock.getStatistic()) + "\t");

				e.getIn().setBody(msgBody);  	
			}
		})
		.to("jms:topic:STATS_TOPIC_ORCL")
		.when(body().regex(".*IBM.*"))
		.process(new Processor(){

			public void process(Exchange e) throws Exception{

				IBMstock.readMsg(e.getIn().getBody(String.class));

				StringBuilder msgBody = new StringBuilder();

				msgBody.append(IBMstock.getName() + "\t");
				IBMstock.setStrategy(new BidMean());
				msgBody.append(mean_df.format(IBMstock.getStatistic()) + "\t");
				IBMstock.setStrategy(new BidVariance());
				msgBody.append(variance_df.format(IBMstock.getStatistic()) + "\t");
				IBMstock.setStrategy(new BidStandardDev());
				msgBody.append(variance_df.format(IBMstock.getStatistic()) + "\t");
				IBMstock.setStrategy(new AskMean());
				msgBody.append(mean_df.format(IBMstock.getStatistic()) + "\t");
				IBMstock.setStrategy(new AskVariance());
				msgBody.append(variance_df.format(IBMstock.getStatistic()) + "\t");
				IBMstock.setStrategy(new AskStandardDev());
				msgBody.append(variance_df.format(IBMstock.getStatistic()) + "\t");

				e.getIn().setBody(msgBody); 
			}
		})
		.to("jms:topic:STATS_TOPIC_IBM")
		.otherwise()
		.to("jms:queue:INVALID_DATA");
	}
}


class ToTradingEngineChannel extends RouteBuilder{

	TradingEngine tradingEngine;
	StockStats current;

	public ToTradingEngineChannel(TradingEngine tradingEngine)
	{
		this.tradingEngine = tradingEngine;
	}

	public void configure() {
		//Iterator<Component> itr = tradingEngine.getPortfolio().list.listIterator();
		
		MyIterator itr = new MyIterator(tradingEngine.getPortfolio());

		while(itr.hasNext()) 
		{	
			current = (StockStats)itr.next();

			from("jms:topic:STATS_TOPIC_" + current.getName())
			.process(new Processor(){
				public void process(Exchange e) throws Exception
				{	
					String msg = e.getIn().getBody(String.class);
					tradingEngine.getPortfolio().readMsg(msg);
					e.getIn().setBody(tradingEngine.report());
				}
			})
			.to("jms:queue:TRADING_ENGINE_QUEUE_" + tradingEngine.getLocation());  
		}
	}
}
