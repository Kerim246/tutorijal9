package sample;

import org.sqlite.JDBC;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransportDAO {

    private static TransportDAO instance;
    private static Connection connection;

    private static PreparedStatement addBus,addDriver,getBusses,getDrivers,deleteBus,deleteDriver,deleteFromBusses,deleteFromDrivers,
            dodijeliVozacuAutobus1,dodijeliVozacuAutobus2,getDriverById,getDriverFromUpit;

    private TransportDAO(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            getBusses = connection.prepareStatement("Select * from bus;");
            getDriverFromUpit = connection.prepareStatement("Select * from vozac;");
            getDrivers = connection.prepareStatement("select * from vozac");
            addBus = connection.prepareStatement("insert into bus values (null, ?, ?, ?, null, null)");
            addDriver = connection.prepareStatement("insert into vozac values (null, ?, ?, ?, ?, ?)");
            deleteBus = connection.prepareStatement("delete from bus where id = ?");
            deleteDriver = connection.prepareStatement("delete from vozac where id = ?");
            deleteFromBusses = connection.prepareStatement("delete from bus");
            deleteFromDrivers = connection.prepareStatement("delete from vozac");
            getDriverById = connection.prepareStatement("select * FROM bus WHERE id = ?");
            dodijeliVozacuAutobus1 = connection.prepareStatement("UPDATE bus SET driverOne = ? WHERE Busses.id = ?");
            dodijeliVozacuAutobus2 = connection.prepareStatement("UPDATE bus SET driverTwo = ? WHERE Busses.id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static {
        try {
            DriverManager.registerDriver(new JDBC());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static TransportDAO getInstance() {
        if(instance == null) instance = new TransportDAO();
        return instance;
    }


    public ArrayList<Bus> getBusses() {
        ArrayList<Bus> busses = new ArrayList<Bus>();
        try {
            ResultSet resultSet = null;
            resultSet = getBusses.executeQuery();
            Bus bus;
            while((bus = getBusFromResultSet(resultSet)) != null){
                busses.add(bus);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busses;
    }

    private Bus getBusFromResultSet(ResultSet resultSet) {
        Bus bus = null;
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String proizvodjac = resultSet.getString(2);
                String serija = resultSet.getString(3);
                int brojSjedista = resultSet.getInt(4);
                bus = new Bus(proizvodjac, serija, brojSjedista);
                bus.setId(id);

                int driverOneId = resultSet.getInt("driverOne");
                int driverTwoId = resultSet.getInt("driverTwo");

                bus.setDriverTwo(getDriverById(driverTwoId));
                bus.setDriverOne(getDriverById(driverOneId));

            }
        } catch (SQLException e) {
            System.out.println("Nije kreiran bus!");
        }

        return bus;
    }

    private Driver getDriverById(int id){
        Driver driver = null;
        try {
            getDriverById.setInt(1, id);
            driver = getDriverFromResultSet(getDriverById.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public void addDriver(Driver driver) {
        try {
            addDriver.setString(1, driver.getName());
            addDriver.setString(2, driver.getSurname());
            addDriver.setString(3, driver.getJmb());
            addDriver.setDate(4, convertToDate(driver.getBirthday()));
            addDriver.setDate(5, convertToDate(driver.getHire_date()));
            addDriver.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Taj vozač već postoji!");
        }
    }

    public void deleteDriver(Driver driver) {
        try {
            deleteDriver.setInt(1, driver.getId());
            deleteDriver.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Driver getDriverFromResultSet(ResultSet resultSet) {
        Driver driver = null;
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String isbn = resultSet.getString(3);
                LocalDate rodjendan = (resultSet.getDate("birthday")).toLocalDate();
                LocalDate zaposlenje = (resultSet.getDate("hire_date")).toLocalDate();
                driver = new Driver(name, surname, isbn,rodjendan,zaposlenje);
                driver.getId(id);

            }
        } catch (SQLException e) {
            System.out.println("Nije kreiran driver!");
        }

        return driver;
    }


    public ArrayList<Driver> getDrivers() {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        try {
            ResultSet resultSet = null;
            resultSet = getDrivers.executeQuery();
            Driver driver;
            while((driver = getDriverFromResultSet(resultSet)) != null){
                drivers.add(driver);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }



    public void addBus(Bus bus) {
        try {
            addBus.setString(1, bus.getMaker());
            addBus.setString(2, bus.getSeries());
            addBus.setInt(3, bus.getSeatNumber());
            addBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteBus(Bus bus) {
        try {
            deleteBus.setInt(1, bus.setId());
            deleteBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {

        if (which == 1) {
            try {
                dodijeliVozacuAutobus1.setInt(1, driver.getId());
                dodijeliVozacuAutobus1.setInt(2, bus.getId());
                dodijeliVozacuAutobus1.executeUpdate();
                bus.setDriverOne(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(which == 2) {
            try {
                dodijeliVozacuAutobus2.setInt(1, driver.getId());
                dodijeliVozacuAutobus2.setInt(2, bus.getId());
                dodijeliVozacuAutobus2.executeUpdate();
                bus.setDriverTwo(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void resetDatabase() {
        try {
            deleteFromBusses.executeUpdate();
            deleteFromDrivers.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
