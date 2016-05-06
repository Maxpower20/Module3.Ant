package Module3_1.Implementation;

import Module3_1.Intefaces.Semaphore;


public class SemaphoreImpl implements Semaphore {

    private volatile int availablePermits;
    private final Object lock = new Object();

    public SemaphoreImpl(int availablePermits) {
        if (availablePermits <= 0) throw new IllegalArgumentException();
        this.availablePermits = availablePermits;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (lock) {
            while (availablePermits == 0) {
                lock.wait();
            }
        availablePermits--;
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        synchronized (lock) {
            if (permits < 0) throw new IllegalArgumentException("Negative quantity of permits or permits requested to acquire is greater than " + getAvailablePermits());

            while (availablePermits < permits) {
                lock.wait();
            }
            availablePermits -= permits;
        }
    }


    @Override
    public void release() {
        synchronized (lock) {

            lock.notifyAll();
           availablePermits++;
        }

    }

    @Override
    public void release(int permits) {
        synchronized (lock) {
            if (permits <= 0) throw new IllegalArgumentException("Must put a positive number of permits");


            lock.notifyAll();
            availablePermits += permits;
        }
    }


    @Override
    public int getAvailablePermits() {
        return availablePermits;
    }


}
