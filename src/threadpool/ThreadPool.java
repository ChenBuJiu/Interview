package threadpool;

import java.util.LinkedList;

public class ThreadPool {
    private TaskState state = TaskState.RUNNING;
    private LinkedList<TaskWorker> workers;
    private LinkedList<Runnable> tasks;
    public ThreadPool(int size) {
        workers = new LinkedList<>();
        tasks = new LinkedList<>();
        for (int i=0;i<size;i++){
            TaskWorker taskThread = new TaskWorker(this);
            taskThread.start();
        }
    }
    public synchronized void execute(Runnable task){
        if (TaskState.RUNNING == state){
            tasks.add(task);
            notifyAll();
        }
    }
    public synchronized Runnable getTask(){
        while (tasks.isEmpty()){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return tasks.remove();
    }
    public synchronized void shutdown(){
        if (TaskState.CLOSED == state){
            return;
        }
        this.state = TaskState.CLOSED;
        for (TaskWorker worker : workers){
            worker.interrupt();
        }
    }
}
