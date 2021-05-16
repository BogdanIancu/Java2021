package ro.ase.acs.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import ro.ase.acs.threads.SummingCallable;
import ro.ase.acs.threads.SummingThread;

public class Main {

	public static void main(String[] args) {
		final int NB_OF_ELEMENTS = 300_000_000;
		int[] array = new int[NB_OF_ELEMENTS];
		
		for(int i = 0; i < NB_OF_ELEMENTS; i++) {
			array[i] = i + 1;
		}
		
		long startTime = System.currentTimeMillis();
		long sum = 0;
		
		for(int i = 0; i < NB_OF_ELEMENTS; i++) {
			sum += array[i];
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(String.format("sum=%d computed in %d ms",
				sum, endTime - startTime));
		
		final int NB_OF_THREADS = 4;
		
		sum = 0;
		startTime = System.currentTimeMillis();
		
		SummingThread[] threads = new SummingThread[NB_OF_THREADS];
		for(int i = 0; i < NB_OF_THREADS; i++) {
			threads[i] = new SummingThread(array,
					i * (NB_OF_ELEMENTS / NB_OF_THREADS),
					(i + 1) * (NB_OF_ELEMENTS / NB_OF_THREADS));
			threads[i].start();
		}
		
		for(int i = 0; i < NB_OF_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < NB_OF_THREADS; i++) {
			sum += threads[i].getSum();
		}
		
		endTime = System.currentTimeMillis();
		System.out.println(String.format("sum=%d computed in %d ms",
				sum, endTime - startTime));
		
		sum = 0;
		startTime = System.currentTimeMillis();
		
		ExecutorService threadPool = 
				Executors.newFixedThreadPool(NB_OF_THREADS);
		SummingThread[] workers = new SummingThread[NB_OF_THREADS];
		for(int i = 0; i < NB_OF_THREADS; i++) {
			workers[i] = new SummingThread(array,
					i * (NB_OF_ELEMENTS / NB_OF_THREADS),
					(i + 1) * (NB_OF_ELEMENTS / NB_OF_THREADS));
			threadPool.execute(workers[i]);
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < NB_OF_THREADS; i++) {
			sum += workers[i].getSum();
		}
		endTime = System.currentTimeMillis();
		System.out.println(String.format("sum=%d computed in %d ms",
				sum, endTime - startTime));
		
		sum = 0;
		startTime = System.currentTimeMillis();
		
		ExecutorService callablePool =
				Executors.newFixedThreadPool(NB_OF_THREADS);
		List<Future<Long>> results = new ArrayList<>();
		for(int i = 0; i < NB_OF_THREADS; i++) {
			SummingCallable callable = 
					new SummingCallable(array, 
							i * (NB_OF_ELEMENTS / NB_OF_THREADS),
							(i+1) * (NB_OF_ELEMENTS / NB_OF_THREADS));
			Future<Long> r = callablePool.submit(callable);
			results.add(r);
		}
		callablePool.shutdown();
		
		for(Future<Long> f : results) {
			try {
				sum += f.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		System.out.println(String.format("sum=%d computed in %d ms",
				sum, endTime - startTime));
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < NB_OF_ELEMENTS / 100; i++) {
			list.add(array[i]);
		}
		
		long s = list.parallelStream().mapToLong(x ->(long)x).sum();
		System.out.println(s);
	}

}
