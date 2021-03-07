package ro.ase.acs.classes;

public class BookStore {
	private String name;
	private String address;
	private int numberOfShops;
	private StoreType type = StoreType.online;
	private float[] prices;
	
	public BookStore() {
		name = "";
		address = "";
		numberOfShops = 0;
		prices = new float[1];
		prices[0] = 50;
	}
	
	public BookStore(String name, int nbOfShops) {
		this.name = name;
		numberOfShops = nbOfShops;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public StoreType getType() {
		return type;
	}

	public void setType(StoreType type) {
		this.type = type;
	}

	public float[] getPrices() {
		if(prices == null) {
			return null;
		}
		float[] copy = new float[prices.length];
		System.arraycopy(prices, 0, copy, 0, prices.length);
		return copy;
	}

	public void setPrices(float[] prices) {
		if(prices != null) {
			this.prices = new float[prices.length];
			for(int i = 0; i < prices.length; i++)
			{
				this.prices[i] = prices[i];
			}
		}
		else {
			this.prices = null;
		}
	}

//	public BookStore myClone()
//	{
//		BookStore copy = new BookStore();
//		copy.name = name;
//		copy.address = address;
//		copy.numberOfShops = numberOfShops;
//		copy.type = type;
//		return copy;
//	}
	
	@Override
	public Object clone() {
		BookStore copy = new BookStore();
		copy.name = name;
		copy.address = address;
		copy.numberOfShops = numberOfShops;
		copy.type = type;
		copy.prices = getPrices();
		return copy;
	}
}
