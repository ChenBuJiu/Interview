package threadpool;

public class ThreadPoolTest{
    public static void main(String[] args){
        ThreadPool threadPool = new ThreadPool(2);
        for (int i = 0; i < 10; i++) {
            int j = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "-task" + j);
                }
            };
            threadPool.execute(task);
/*            if (j == 7) {
                threadPool.shutdown();
            }*/
        }
    }
}
