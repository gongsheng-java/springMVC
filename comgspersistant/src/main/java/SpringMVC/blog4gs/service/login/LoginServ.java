package SpringMVC.blog4gs.service.login;

import SpringMVC.blog4gs.dao.login.ILoginDao;
import SpringMVC.blog4gs.model.Constant;
import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.login.LoginBean;
import SpringMVC.blog4gs.model.login.SessionInfo;
import SpringMVC.blog4gs.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/18.
 */
@Service
public class LoginServ {
    @Autowired
    private ILoginDao loginDao;
    @Autowired
    private HttpServletRequest requestByAuto;
    public RetInfo loginSession(LoginBean loginBean){
      //  HttpServletRequest hsr = new
        RetInfo retInfo = new RetInfo();
        SessionInfo sessionInfo =loginDao.getLoginInfo(loginBean);
        if(sessionInfo!=null){
            retInfo= CommonUtil.setRetInfo("000000","用户名密码正确");
            requestByAuto.getSession().setAttribute("loginInfo",sessionInfo);
            System.out.println("=====保存session成功==="+sessionInfo);
            return retInfo;
        }else {
            retInfo= CommonUtil.setRetInfo(Constant.NOTLOGIN,"用户名密码正确");
        }
        return retInfo;
    }
}
