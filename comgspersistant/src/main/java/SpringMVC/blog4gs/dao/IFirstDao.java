package SpringMVC.blog4gs.dao;

import SpringMVC.blog4gs.model4db.FirstModel;

/**
 * Created by Administrator on 2017/5/6.
 */
/*@Repository("firstDao")*/
public interface IFirstDao {
    public FirstModel getFirstModel(int id);
}
