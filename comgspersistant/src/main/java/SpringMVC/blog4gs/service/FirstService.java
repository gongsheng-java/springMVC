package SpringMVC.blog4gs.service;

import SpringMVC.blog4gs.dao.IFirstDao;
import SpringMVC.blog4gs.model4db.FirstModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/6.
 *
 * @author gs
 * @see  111
 * @param 'id'
 */
@Service
public class FirstService {
    @Autowired
    private IFirstDao firstDao;
    public FirstModel firstTest(){
        FirstModel firstModel =firstDao.getFirstModel(0);
        return firstModel;
    }
}
