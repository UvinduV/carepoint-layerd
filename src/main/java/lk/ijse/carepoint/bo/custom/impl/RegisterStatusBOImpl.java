package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.RegisterStatusBO;
import lk.ijse.carepoint.dao.DAOFactory;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.CustomerDAO;
import lk.ijse.carepoint.dao.custom.VehicleDAO;
import lk.ijse.carepoint.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.carepoint.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.carepoint.dto.CustomerDto;
import lk.ijse.carepoint.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterStatusBOImpl implements RegisterStatusBO {
    private CustomerDAO customerDAO =(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    @Override
    public  String generateCustID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }
    @Override
    public List<CustomerDto> getAllCutomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(dto);
    }
    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
       return vehicleDAO.save(dto);
    }

}
