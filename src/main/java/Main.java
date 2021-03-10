public class Main {

    public static void main(String[] args) {

        final Store store = new Store();

        new Thread(null, store::getCar, "Егор").start();
        new Thread(null, store::getCar, "Андрей").start();
        new Thread(null, store::getCar, "Виктор").start();
        new Thread(null, store::getCar, "Степан").start();

        new Thread(null, store::putCar, "Toyota").start();
    }
}
