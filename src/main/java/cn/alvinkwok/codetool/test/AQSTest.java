package cn.alvinkwok.codetool.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description
 *
 * @author alvinkwok
 * @since 2024/2/28
 */
public class AQSTest {


    private static ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();

    public static class ReadRunnable implements Runnable {

        private long waitTime ;

        public ReadRunnable(long waitTime) {
            this.waitTime = waitTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rrwLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get read lock");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rrwLock.readLock().unlock();
                System.out.println(Thread.currentThread().getName() + " release read lock");
            }
        }
    }

    public static class WriteRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rrwLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get write lock");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rrwLock.writeLock().unlock();
                System.out.println(Thread.currentThread().getName() + " release write lock");
            }
        }
    }

    public static void main(String[] args) {

        // 先读锁再写锁
        new Thread(new ReadRunnable(1000)).start();
        new Thread(new ReadRunnable(5000)).start();
    }


}
