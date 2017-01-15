package dao;

import java.util.List;
import model.Operation;

/**
 *
 * @author Sergey
 */
public interface IOperationDao {
    List<Operation> getAllOperatin(int idUser);
    int getBalans(int idoperation);
    void addOperation(Operation operation);
}
