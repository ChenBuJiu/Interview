package threadpool;

public class TaskWorker extends Thread {
    private ThreadPool threadPool;

    public TaskWorker(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            this.threadPool.getTask().run();
        }
    }
}
