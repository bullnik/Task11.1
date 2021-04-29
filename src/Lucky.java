public class Lucky {
    static final Data data = new Data();

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int xCopy;
                synchronized (data) {
                    data.setX(data.getX() + 1);
                    xCopy = data.getX();
                }

                if (xCopy > 999999) {
                    break;
                }

                if ((xCopy % 10) + (xCopy / 10) % 10 + (xCopy / 100) % 10 == (xCopy / 1000)
                        % 10 + (xCopy / 10000) % 10 + (xCopy / 100000) % 10) {
                    System.out.println(xCopy);

                    synchronized (data) {
                        data.setCount(data.getCount() + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + data.getCount());
    }
}
