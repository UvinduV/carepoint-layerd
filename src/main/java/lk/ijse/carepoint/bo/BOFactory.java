package lk.ijse.carepoint.bo;

import lk.ijse.carepoint.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory=new BOFactory() : boFactory;
    }
    public enum BOTypes{
        ITEM,LOGIN,PLACE_SERVICE,REGISTER,APPOINMENT,SHEDULE,SIGNUP
    }
    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case ITEM:
                return new ItemBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PLACE_SERVICE:
                return new PlaceServiceBOImpl();
            case REGISTER:
                return new RegisterStatusBOImpl();
            case APPOINMENT:
                return new ServiceAppointBOImpl();
            case SHEDULE:
                return new SheduleBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();
            default:
                return null;
        }
    }
}

