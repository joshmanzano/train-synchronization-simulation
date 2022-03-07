
public class Lock {
	
	int i;
	
	public void lock_init() {
		i = 1;
	}
	
	public synchronized void lock_acquire() {
		while(i <= 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {}
		}
		i -= 1;
	}
	
	public synchronized void lock_release() {
		i += 1;
		this.notify();
	}
	
}
