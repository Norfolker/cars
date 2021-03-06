public class Store {

    Seller seller = new Seller(this);

    public void getCar() {
        seller.getCar();
    }

    public void putCar() {
        seller.putCar();
    }
}