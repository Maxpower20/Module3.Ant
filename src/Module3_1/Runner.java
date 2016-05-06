package Module3_1;


import Module3_1.Implementation.SemaphoreImpl;
import Module3_1.Intefaces.Semaphore;


public class Runner {

    private Semaphore semaphore = new SemaphoreImpl(1);
    public static void main(String[] args) {

    Runner runner = new Runner();

    runner.test();

    }

    public void test() {
        for (int i = 0; i < 4; i++){
            new Thread(new SemaphoreTester(semaphore)).start();
        }
    }
}



