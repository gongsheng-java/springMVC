package SpringMVC.blog4gs.controller.login;

import SpringMVC.blog4gs.model.RetInfo;
import SpringMVC.blog4gs.model.login.LoginBean;
import SpringMVC.blog4gs.model.login.SessionInfo;
import SpringMVC.blog4gs.service.login.LoginServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/19.
 */
@Controller
@RequestMapping("/login")
public class LoginControl {
    @Autowired
    private HttpServletRequest requestByAuto;
    @Resource
    private LoginServ loginServ;
    @RequestMapping(value="/getloginInfo",method = RequestMethod.POST)
    @ResponseBody
    public RetInfo loginInfo(@RequestBody LoginBean loginInfo){
        RetInfo retInfo = new RetInfo();
        HttpSession session = requestByAuto.getSession();
        SessionInfo loginSession = (SessionInfo)session.getAttribute("loginInfo");
        if(StringUtils.isEmpty(loginSession)){
            retInfo =loginServ.loginSession(loginInfo);
            //requestByAuto.getSession().getAttribute("loginInfo"))
        }else{
            System.out.println("==异常信息登录,会走过滤器校验==");
        }
        //requestByAuto.getSession().setAttribute();
        return retInfo;
    }
}
