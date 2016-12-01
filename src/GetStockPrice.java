
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;

public class GetStockPrice {

	public static void main(String args[]) throws IOException {

		SortedSet<StockInfo> stockByName = new TreeSet<>(Comparator.comparing(StockInfo::getName));
		SortedSet<StockInfo> stockByPrice = new TreeSet<>(Comparator.comparing(StockInfo::getPrice));
		String[] stockList = { "AAPL", "YHOO", "BABA", "ERIC" };

		for (int i = 0; i < stockList.length; i++) {
			String stockName = stockList[i];

			URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20"
					+ "yahoo.finance.quotes%20where%20symbol%20in%20(%22" + stockName
					+ "%22)&format=json&env=store://datatables.org/alltableswithkeys");

			// Get the input stream through URL Connection
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line = null;

			// read each line and write to System.out
			while ((line = br.readLine()) != null) {

				int begin = line.indexOf("Ask") + 6;
				int end = line.indexOf("AverageDailyVolume") - 3;
				String sub = line.substring(begin, end);
				//System.out.println(sub);

				float number = Float.parseFloat(sub);
				// System.out.println(number);
				StockInfo entry = new StockInfo(stockName, number);
				stockByName.add(entry);
				stockByPrice.add(entry);
			}

		}
		
		Iterator<StockInfo> iter = stockByName.iterator();
		System.out.println("Sort by Name");
		while (iter.hasNext()) {
			StockInfo entry = iter.next();
			System.out.println(entry.getName() + " " + entry.getPrice());
		}
		
		iter = stockByPrice.iterator();
		System.out.println("\nSort by Price");
		while (iter.hasNext()) {
			StockInfo entry = iter.next();
			System.out.println(entry.getName() + " " + entry.getPrice());
		}
	}

}
