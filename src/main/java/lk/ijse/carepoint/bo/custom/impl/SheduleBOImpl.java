package lk.ijse.carepoint.bo.custom.impl;

import lk.ijse.carepoint.bo.custom.SheduleBO;
import lk.ijse.carepoint.dao.SqlUtil;
import lk.ijse.carepoint.dao.custom.SheduleDAO;
import lk.ijse.carepoint.dao.custom.impl.SheduleDAOImpl;
import lk.ijse.carepoint.dto.SheduleDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SheduleBOImpl implements SheduleBO {
    private SheduleDAO sheduleDAO=new SheduleDAOImpl();
    @Override
    public String generateSheduelID() throws SQLException, ClassNotFoundException {
        return sheduleDAO.generateNewID();
    }
    @Override
    public boolean saveSlot(SheduleDto dto) throws SQLException, ClassNotFoundException {
        return sheduleDAO.save(dto);
    }
    @Override
    public List<SheduleDto> getAllShedule(Date dateIn) throws SQLException, ClassNotFoundException {
        return sheduleDAO.getAllShedule(dateIn);
    }
}
