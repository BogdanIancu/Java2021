package ro.ase.acs.threads;

public class SummingThread extends Thread {
	private int[] array;
	private int startIndex;
	private int endIndex;
	private long sum = 0;
	
	public SummingThread(int[] array, int startIndex, int endIndex) {
		this.array = array;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public long getSum() {
		return sum;
	}
	
	@Override
	public void run() {
		super.run();
		for(int i = startIndex; i < endIndex; i++) {
			sum += array[i];
		}
	}
}
