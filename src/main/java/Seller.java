public class Seller {

    protected static int carsSum = 6;
    protected static int target = 10;
    protected static long sellTime = 600L;
    protected static long putTime = 200L;
    protected static int salesCount = 0;

    private Store store;

    public Seller (Store store) {
        this.store = store;
    }

    public synchronized void getCar() {
        if (salesCount < target) {
            try {
                System.out.println("Покупатель " + Thread.currentThread().getName() + " зашел в автосалон");
                while (carsSum == 0) {
                    System.out.println("Машин нет");
                    wait();
                }
                carsSum--;
                salesCount++;
                Thread.sleep(sellTime);
                System.out.println("Продан " + salesCount + "й автомобиль");
                System.out.println("Атомобилей в салоне: " + carsSum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void putCar() {
        try {
            Thread.sleep(putTime);
            carsSum++;
            System.out.println("Производитель " + Thread.currentThread().getName() + " добавил 1 автомобиль");
            System.out.println("Атомобилей в салоне: " + carsSum);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
