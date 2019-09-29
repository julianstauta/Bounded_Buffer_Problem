import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread{
	
	private Semaphore fillcount;
	private Semaphore emptycount;
	private Random randGen;
	private Queue<Integer> queue;
	
	public Consumer(Semaphore fillcount, Semaphore emptycount, Queue<Integer> queue) {
		this.fillcount = fillcount;
		this.emptycount = emptycount;
		randGen = new Random(12345);
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if (fillcount.availablePermits()>0) {
					System.out.println("El consumidor consume consume" + queue.poll());
					fillcount.acquire();
					emptycount.release();
				} else {
					System.out.println("El consumidor no pudo consumir porque no hay nada en la cola");
				}
				sleep(Math.abs(randGen.nextInt()) % 1000 + 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
