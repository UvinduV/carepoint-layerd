package lk.ijse.carepoint.dao;

import lk.ijse.carepoint.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory==null? daoFactory=new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        APPOINT,CUSTOMER,ITEM,PACKAGE,SERVICE_DETAIL,SHEDULE,USER,VEHICLE
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case APPOINT:
                return new AppointmentDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case PACKAGE:
                return new PackageDAOImpl();
            case SERVICE_DETAIL:
                return new ServiceDetailDAOImpl();
            case SHEDULE:
                return new SheduleDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            default:
                return null;
        }
    }
}
