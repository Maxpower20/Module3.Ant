package Module3_1;


import Module3_1.Intefaces.Semaphore;

public class SemaphoreTester implements Runnable{
    private Semaphore semaphore;

    public SemaphoreTester(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " started");
            Thread.sleep(2000);

            semaphore.acquire();
            Thread.sleep(2000);

            System.out.println("Threads " + Thread.currentThread().getName() + " awaken");
            Thread.sleep(2000);

            semaphore.release();

            System.out.println("Threads " + Thread.currentThread().getName() + " available");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
