package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.ServiceAppointBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.*;
import lk.ijse.carepoint.dao.custom.impl.*;
import lk.ijse.carepoint.dto.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceAppointBOImpl implements ServiceAppointBO {
    private CustomerDAO customerDAO=new CustomerDAOImpl();
    private VehicleDAO vehicleDAO=new VehicleDAOImpl();
    private SheduleDAO sheduleDAO =new SheduleDAOImpl();
    private PackageDAO packageDAO=new PackageDAOImpl();
    private AppointmentDAO appointmentDAO=new AppointmentDAOImpl();
    public CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return customerDAO.search(newValue);
    }

    public  CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerID(Tel);
    }
    public List<VehicleDto> getAllVehicle() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getAll();
    }
    public SheduleDto searchSheduleID(String newValue) throws SQLException, ClassNotFoundException {
        return sheduleDAO.search(newValue);
    }
    public PackageDto searchPackage(String newValue) throws SQLException, ClassNotFoundException {
       return packageDAO.search(newValue);
    }
    public List<PackageDto> getAllPackage() throws SQLException, ClassNotFoundException {
        return packageDAO.getAll();
    }
    public String generateAppointID() throws SQLException, ClassNotFoundException {
        return appointmentDAO.generateNewID();
    }
    public boolean saveAppointment(serviceAppointDto dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(dto);
    }
}
