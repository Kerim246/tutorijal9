package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bus {

        private SimpleIntegerProperty id = new SimpleIntegerProperty();
        private SimpleStringProperty maker = new SimpleStringProperty();
        private SimpleStringProperty series = new SimpleStringProperty();
        private SimpleIntegerProperty seatNumber = new SimpleIntegerProperty();
        private SimpleIntegerProperty numberOfDrivers = new SimpleIntegerProperty();
        private SimpleIntegerProperty driverOne = new SimpleIntegerProperty();
        private SimpleIntegerProperty driverTwo = new SimpleIntegerProperty();

        public Bus() {
        }

        public Bus(String maker, String series, int seatNumber) {
            this.maker = new SimpleStringProperty(maker);
            this.series = new SimpleStringProperty(series);
            this.seatNumber = new SimpleIntegerProperty(seatNumber);

        }

        public Bus(int id,String maker, String series, int seatNumber,int numberOfDrivers, int driverOne, int driverTwo) {
            this.id = new SimpleIntegerProperty(id);
            this.maker = new SimpleStringProperty(maker);
            this.series = new SimpleStringProperty(series);
            this.seatNumber = new SimpleIntegerProperty(seatNumber);
            this.numberOfDrivers = new SimpleIntegerProperty(numberOfDrivers);
            this.driverOne = new SimpleIntegerProperty(driverOne);
            this.driverTwo = new SimpleIntegerProperty(driverTwo);
        }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMaker() {
        return maker.get();
    }

    public SimpleStringProperty makerProperty() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker.set(maker);
    }

    public String getSeries() {
        return series.get();
    }

    public SimpleStringProperty seriesProperty() {
        return series;
    }

    public void setSeries(String series) {
        this.series.set(series);
    }

    public int getSeatNumber() {
        return seatNumber.get();
    }

    public SimpleIntegerProperty seatNumberProperty() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber.set(seatNumber);
    }

    public int getNumberOfDrivers() {
        return numberOfDrivers.get();
    }

    public SimpleIntegerProperty numberOfDriversProperty() {
        return numberOfDrivers;
    }

    public void setNumberOfDrivers(int numberOfDrivers) {
        this.numberOfDrivers.set(numberOfDrivers);
    }

    public int getDriverOne() {
        return driverOne.get();
    }

    public SimpleIntegerProperty driverOneProperty() {
        return driverOne;
    }

    public void setDriverOne(int driverOne) {
        this.driverOne.set(driverOne);
    }

    public int getDriverTwo() {
        return driverTwo.get();
    }

    public SimpleIntegerProperty driverTwoProperty() {
        return driverTwo;
    }

    public void setDriverTwo(int driverTwo) {
        this.driverTwo.set(driverTwo);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "maker=" + maker +
                ", series=" + series +
                '}';
    }
}



