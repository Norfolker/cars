public class Seller {

    protected static int carsSum = 4;
    protected static int target = 10;
    protected static long sellTime = 600L;
    protected static long putTime = 200L;
    protected static int salesCount = 0;

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
                    notify();
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
        while (salesCount < target) {
            try {
                Thread.sleep(putTime);
                carsSum++;
                System.out.println("Производитель " + Thread.currentThread().getName() + " добавил 1 автомобиль");
                System.out.println("Атомобилей в салоне: " + carsSum);
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
    }
}
