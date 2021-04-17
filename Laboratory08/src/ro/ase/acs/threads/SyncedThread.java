package ro.ase.acs.threads;

public class SyncedThread implements Runnable {
	private static int a = 0;
	private static int b = 0;
	private static Object lock = new Object();
	private String name;
	
	public SyncedThread(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			synchronized (lock) {
				System.out.println(String.format(
						"%s: a = %d, b = %d", name, a, b));
				a++;
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				b++;
			}
		}
	}
	
	public synchronized void f() {
//		synchronized (this) {
//			
//		}
	}

}
