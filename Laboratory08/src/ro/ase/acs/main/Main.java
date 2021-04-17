package ro.ase.acs.main;

import ro.ase.acs.threads.SyncedThread;
import ro.ase.acs.threads.UnsyncedThread;

public class Main {

	public static void main(String[] args) {
		//UnsyncedThread t1 = new UnsyncedThread();
		//t1.start();
		
		//UnsyncedThread t2 = new UnsyncedThread();
		//t2.start();
		
		SyncedThread t3 = new SyncedThread("T3");
		SyncedThread t4 = new SyncedThread("T4");
		
		new Thread(t3).start();
		new Thread(t4).start();
		
		System.out.println("Hello, world!");
		
		Runnable r = () -> {
			try {
				int z = 5 / 0;
			} catch(Exception e) {
				System.out.println("Caught it!");
			}
			System.out.println("Hello from another thread!");
		};

		new Thread(r).start();
		
		new Thread(() -> System.out.println("Another thread")).start();
	}

}
