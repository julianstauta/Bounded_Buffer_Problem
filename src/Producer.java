import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {

	private Semaphore fillcount;
	private Semaphore emptycount;
	private Random randGen;
	private Queue<Integer> queue;
	
	public Producer(Semaphore fillcount, Semaphore emptycount, Queue<Integer> queue) {
		this.fillcount = fillcount;
		this.emptycount = emptycount;
		randGen = new Random(1234);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if (emptycount.availablePermits()>0) {
					int n = Math.abs((randGen.nextInt()) % 1000);
					queue.add(n);
					fillcount.release();
					emptycount.acquire();
					System.out.println("El productor produce" + n);
				} else {
					System.out.println("EL productor no pudo producir porque la cola esta llena");
				}
				sleep(Math.abs(randGen.nextInt()) % 1000 + 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
