package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.ServiceAppointBO;
import lk.ijse.carepoint.dao.DAOFactory;
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
    private CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private VehicleDAO vehicleDAO=(VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private SheduleDAO sheduleDAO = (SheduleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SHEDULE);
    private PackageDAO packageDAO= (PackageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PACKAGE);
    private AppointmentDAO appointmentDAO=(AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINT);
    @Override
    public CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return customerDAO.search(newValue);
    }
    @Override
    public  CustomerDto searchCustomerID(String Tel) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomerID(Tel);
    }
    @Override
    public List<VehicleDto> getAllVehicle() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getAll();
    }
    @Override
    public SheduleDto searchSheduleID(String newValue) throws SQLException, ClassNotFoundException {
        return sheduleDAO.search(newValue);
    }
    @Override
    public PackageDto searchPackage(String newValue) throws SQLException, ClassNotFoundException {
       return packageDAO.search(newValue);
    }
    @Override
    public List<PackageDto> getAllPackage() throws SQLException, ClassNotFoundException {
        return packageDAO.getAll();
    }
    @Override
    public String generateAppointID() throws SQLException, ClassNotFoundException {
        return appointmentDAO.generateNewID();
    }
    @Override
    public boolean saveAppointment(serviceAppointDto dto) throws SQLException, ClassNotFoundException {
        return appointmentDAO.save(dto);
    }
}
