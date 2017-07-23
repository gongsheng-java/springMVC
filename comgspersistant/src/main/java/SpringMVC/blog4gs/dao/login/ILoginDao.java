package SpringMVC.blog4gs.dao.login;

import SpringMVC.blog4gs.model.login.LoginBean;
import SpringMVC.blog4gs.model.login.SessionInfo;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface ILoginDao {
    public SessionInfo getLoginInfo(LoginBean loginBean);
}
