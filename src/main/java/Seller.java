public class Seller {

    protected static final int target = 10;
    protected static final long sellTime = 300L;
    protected static final long putTime = 900L;
    protected static int salesCount = 0;
    protected static int carsSum = 0;

    private Store store;

    public Seller (Store store) {
        this.store = store;
    }

    public synchronized void getCar() {
        while (salesCount < target) {
            System.out.println("Покупатель " + Thread.currentThread().getName() + " зашел в автосалон");
            try {
                if (carsSum < 1) {
                    System.out.println("Машин нет");
                    wait();
                } else {
                    salesCount++;
                    carsSum--;
                    System.out.println("Продан " + salesCount + "й автомобиль");
                    System.out.println("Атомобилей в салоне: " + carsSum);
                    Thread.sleep(sellTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().isInterrupted();
    }

    public synchronized void putCar() {
        while (carsSum < (target - salesCount)) {
            try {
                Thread.sleep(putTime);
                carsSum++;
                System.out.println("Производитель " + Thread.currentThread().getName() + " добавил 1 автомобиль");
                System.out.println("Атомобилей в салоне: " + carsSum);
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }
}
