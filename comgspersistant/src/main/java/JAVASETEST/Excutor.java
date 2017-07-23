package JAVASETEST;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Created by Administrator on 2017/6/1.
 */
public class Excutor {
    // 列出execute最核心的几个属性、以及常用方法、和execute的执行流程

        // 核心线程数，当工作线程达到这个值，会将任务放到阻塞队列里面，当阻塞队列也满了，创建新线程执行任务，当达到最大线程数时，就该饱和异常了，饱和策略需要自己定义，实现其他
        private volatile int corePoolSize;
        // 最大线程数
        private volatile int maximumPoolSize;
        // 线程对象工厂类：用来创建一个线程
        private volatile ThreadFactory threadFactory;
        // 工作队列，这个是需要构造器入参入进来的，此处简化成默认的一个
        private final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
        // 默认饱和策略
        private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
        // 重入锁
        private final ReentrantLock mainLock = new ReentrantLock();

        // 工作者们，实际是一个无序的hashset
        private final HashSet<Worker> workers = new HashSet<Worker>();

        private int largestPoolSize;

        // 这个是个整体的int值，高三位表示线程池的运行状态，低29位用来表示线程池中的线程数
        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

        // 32-3=29，代表低29位，用来定义 ：从-1到3 和 向左移动29位 =》
        private static final int COUNT_BITS = Integer.SIZE - 3;
        // 线程池的容量0001向左移29位
        private static final int CAPACITY = (1 << COUNT_BITS) - 1;

        // 运行状态，高三位111，该状态的线程池会接受新任务，并处理阻塞队列中的任务
        private static final int RUNNING = -1 << COUNT_BITS;
        // 关闭状态 高三位000，该状态的线程池不会接受新任务，但是会处理阻塞队列中的任务
        private static final int SHUTDOWN = 0 << COUNT_BITS;
        // 停止状态 高三位001，新任务不接，阻塞任务不执行，当前运行的任务也会停掉
        private static final int STOP = 1 << COUNT_BITS;
        // 整顿状态 高三位010
        private static final int TIDYING = 2 << COUNT_BITS;
        // 结束状态 高三位011
        private static final int TERMINATED = 3 << COUNT_BITS;

        // 获取线程池的运行状态
        private static int runStateOf(int c) {
            return c & ~CAPACITY;
        }

        // 获取线程池的线程数量
        private static int workerCountOf(int c) {
            return c & CAPACITY;
        }

        // 根据当前的运行状态值和线程数量值，使用| 得到当前整体的ctl值
        private static int ctlOf(int rs, int wc) {
            return rs | wc;
        }

        public void execute(Runnable command) {
            // 判断被执行的任务对象是否为空，空则抛出空指针
            if (command == null)
                throw new NullPointerException();
            // 开始逻辑分支
            int c = ctl.get();// 获取当前的线程池的整体码
            if (workerCountOf(c) < corePoolSize) {// 1、根据整体值c算出当前线程数，当当前线程数小于核心线程数，创建新的线程
                if (addWorker(command, true))// 执行成功就返回
                    return;
                // 再获取一次当前线程池的整体值C
                c = ctl.get();
            }
            // 2.如果不满足1的条件，说明当前线程数超过了核心线程数， 再判断线程池（对象）是否为运行状态，这个时候把任务放到阻塞队列中去
            if (isRunning(c) && workQueue.offer(command)) {
                int recheck = ctl.get();// 再次检查，必须严谨
                if (!isRunning(recheck) && remove(command))// 如果线程池不在运行状态，那么就开始将需要执行的任务对象移除工作队列，注意这一步必须先判断，如果后判断是有问题？具体啥问题一会再看
                    reject(command);
                else if (workerCountOf(recheck) == 0)// ?为啥要放这里
                    addWorker(null, false);// ？？？
            } else if (!addWorker(command, false)) {// 3.如果放到阻塞队列里面失败了，那么就elseif了，说明阻塞队列达到最大长度，创建额外的线程来执行任务，注意此处的core由最一开始的ture变为false了
                reject(command);// 代表线程数已经达到最大线程数maximumPoolSize，无法继续创建任务了，那么需要执行饱和方法，该抛异常就抛出异常
            }
        }

        // 创建一工作线程来执行任务对象
        private boolean addWorker(Runnable firstTask, boolean core) {
            retry: // 代表跳出循环后，代码从哪里开始执行
            for (;;) {
                // 获取当前线程池整体值以及运行时的状态值
                int c = ctl.get();
                int rs = runStateOf(c);
                if (rs >= SHUTDOWN && !(rs == SHUTDOWN && firstTask == null && !workQueue.isEmpty()))
                    return false;

                for (;;) {
                    int wc = workerCountOf(c);// 获得当前线程数
                    // 如果当前线程数大于容量 或者 当前线程数大于 （是核心线程就核心线程数，不是的话 就是最大线程数）
                    if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize))
                        return false; // 跳出循环
                    if (compareAndIncrementWorkerCount(c))// 如果不符合上面那个if条件
                        // ，就代表可以创建一个线程，这个时候就直接跳出这个死循环
                        break retry;
                    c = ctl.get(); // Re-read ctl
                    continue retry;
                }
            }

            boolean workerStarted = false;
            boolean workerAdded = false;
            Worker w = null;
            try {
                w = new Worker(firstTask);// 创建一个线程-------并设置了runnable的对象
                final Thread t = w.thread;// 将这个线程设置为final防止 改变
                if (t != null) {
                    final ReentrantLock mainLock = this.mainLock;// 创建一个重入锁
                    mainLock.lock();// 上锁
                    try {// 中间这一堆东西=校验+计数
                        int rs = runStateOf(ctl.get());
                        if (rs < SHUTDOWN || (rs == SHUTDOWN && firstTask == null)) {
                            if (t.isAlive()) // precheck that t is startable
                                throw new IllegalThreadStateException();

                            if (rs < SHUTDOWN || (rs == SHUTDOWN && firstTask == null)) {
                                if (t.isAlive()) // precheck that t is startable
                                    throw new IllegalThreadStateException();
                                workers.add(w);
                                int s = workers.size();
                                if (s > largestPoolSize)
                                    largestPoolSize = s;
                                workerAdded = true;
                            }
                        }
                    } finally {
                        mainLock.unlock();// 解锁
                    }
                    if (workerAdded) {// ===============注意此处！！！t.start（）了
                        t.start();// ====================此处的start，是从woker里面获取到的线程，而当t.start,本质上（为啥任务对象都要重写run方法，因为在t.start的时候，
                        // 会调用C起一个线程，然后执行run方法）是执行了woker的的run方法，而run方法又执行了runwoker方法，runworker方法才是整个线程池的核心！
                        workerStarted = true;
                    }
                }
            } finally {
                if (!workerStarted)
                    addWorkerFailed(w);
            }
            return workerStarted;
        }

        public void runWorker(Worker w) {
            Thread wt = Thread.currentThread();// 获取当前线程：此处的线程指的应该是主线程。
            Runnable task = w.firstTask;// 获取被提交到worker里的那个任务对象
            w.firstTask = null;
            w.unlock();
            boolean completedAbruptly = true;
            try {
                while (task != null || (task = getTask()) != null) {// 注意此处：任务对象不为空
                    // 如果为空，那么再从等待队列中获取一个任务对象赋值给当前要运行的任务对象，并且此处是用的while循环，也就是说，如果core线程满了，
                    // 放到等待队列后，再线程池运行的过程中，会一直循环的去获取任务对象，来执行任务
                    w.lock();// 任务对象上锁
                    // 状态判断
                    if ((runStateAtLeast(ctl.get(), STOP) || (Thread.interrupted() && runStateAtLeast(ctl.get(), STOP)))
                            && !wt.isInterrupted())
                        wt.interrupt();
                    try {
                        beforeExecute(wt, task);// 在jdk中是空方法体，可以重写，来增加业务逻辑
                        Throwable thrown = null;
                        try {
                            task.run();// 此处的run就是！平时重写的那个run方法，是会放到这里来执行的！
                        } finally {
                            afterExecute(task, thrown);
                        }
                    } finally {
                        task = null;// --------------去除任务对象的堆栈引用，这样在堆中的对象实例就相当于是个垃圾对象，gc来的时候直接就被回收了
                        w.completedTasks++;
                        w.unlock();// 释放锁，完事儿了！
                    }
                }
                completedAbruptly = false;
            } finally {
                processWorkerExit(w, completedAbruptly);
            }
        }

        private final class Worker extends AbstractQueuedSynchronizer implements Runnable {
            /**
             * This class will never be serialized, but we provide a
             * serialVersionUID to suppress a javac warning.
             */
            private static final long serialVersionUID = 6138294804551838833L;

            /** Thread this worker is running in. Null if factory fails. */
            final Thread thread;
            /** Initial task to run. Possibly null. */
            Runnable firstTask;
            /** Per-thread task counter */
            volatile long completedTasks;

            /**
             * Creates with given first task and thread from ThreadFactory.
             *
             * @param firstTask
             *            the first task (null if none)
             */
            Worker(Runnable firstTask) {// 构造方法
                setState(-1);
                this.firstTask = firstTask;// 设置任务对象
                this.thread = getThreadFactory().newThread(this);// 设置工厂方法，最初在创建线程池对象时传入的线程工厂对象就是在这里使用的
            }

            // 重写run方法执行任务
            public void run() {
                runWorker(this);
            }

            protected boolean isHeldExclusively() {
                return getState() != 0;
            }

            protected boolean tryAcquire(int unused) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            protected boolean tryRelease(int unused) {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }

            public void lock() {
                acquire(1);
            }

            public boolean tryLock() {
                return tryAcquire(1);
            }

            public void unlock() {
                release(1);
            }

            public boolean isLocked() {
                return isHeldExclusively();
            }

            void interruptIfStarted() {
                Thread t;
                if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                    try {
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    }
                }
            }
        }

        // ============================分割线，上面是线程池的核心方法，下面的是辅助方法，可不用看

        private void reject(Runnable command) {
            // 加载默认饱和策略
        }

        // 线程池是否为运行状态
        private static boolean isRunning(int c) {
            return c < SHUTDOWN; // 如果可运行 高三位比较： ?: 111<000
        }

        private void addWorkerFailed(Worker w) {

        }

        private boolean compareAndIncrementWorkerCount(int expect) {
            return ctl.compareAndSet(expect, expect + 1);
        }

        public boolean remove(Runnable task) {
            boolean removed = workQueue.remove(task);
            tryTerminate(); // In case SHUTDOWN and now empty
            return removed;
        }

        private void tryTerminate() {
            // TODO Auto-generated method stub
        }

        public ThreadFactory getThreadFactory() {
            return threadFactory;
        }

        private void processWorkerExit(Worker w, boolean completedAbruptly) {

        }

        private static boolean runStateAtLeast(int c, int s) {
            return c >= s;
        }

        protected void beforeExecute(Thread t, Runnable r) {
        }

        private void afterExecute(Runnable task, Throwable thrown) {

        }

        private Runnable getTask() {

            return null;
        }
    }


