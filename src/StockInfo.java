
public class StockInfo {

	private String _name;
	private Float _price;
	
	public StockInfo(String name, Float price) {
		this._name = name;
		this._price = price;
	}
	
	public Float getPrice() {
		return _price;
	}
	
	public String getName() {
		return _name;
	}
}
