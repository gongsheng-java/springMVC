package SpringMVC.blog4gs.controller.classify;

import SpringMVC.blog4gs.model4db.FirstModel;
import SpringMVC.blog4gs.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */

@Controller
@RequestMapping(value = "/mvc")
@SessionAttributes("123")
public class FirstSpringMVC {
    @Autowired
    private HttpServletRequest requestByAuto;
    @Autowired
    private HttpServletResponse responseByAuto;
    @Resource
    private FirstService testService;   //ctrl+q


    @RequestMapping(value = "/hello/{userId}",method = RequestMethod.GET,params = {"abc","bcd"})
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") String userId, @RequestParam("abc") String a){

        // System.out.println("hello mvc");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "hello" );
        FirstModel fm = testService.firstTest();
        System.out.println(fm);
        Map map = new HashMap<String,String>();
        map.put("name","gs");
        modelAndView.addObject("name" , map);
        System.out.println("hello mvc3");
        String hsr = request.getParameter("abc");
        String hsrAuto = requestByAuto.getParameter("bcd");
        //System.out.println(hsr);
       System.out.println(hsrAuto);
        return modelAndView;
    }

   /* @Scope("prototype")*/
    @RequestMapping(value = "/ajaxhello/{userId}",method = RequestMethod.POST,params = {"abc=123","bcd"}/*,headers={ "host=localhost" , "Accept" }*/)
    @ResponseBody
    public Map<?, Object> hello2(@RequestBody FirstModel fm, HttpServletRequest request, HttpServletResponse response, InputStream in, OutputStream out, @PathVariable("userId") String userId, @RequestParam("abc") String a/*,@RequestHeader("Accept-Encoding") String encoding,@CookieValue("JSESSIONID") String cookie*/) throws IOException {
       // System.out.println(cookie);
        System.out.println("=============="+fm);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br =new  BufferedReader(isr);
        testService.firstTest();
        try {
           /*String tmp;
           *//* while ((tmp=br.readLine())!=null) {
                System.out.println(tmp);
            }*/
            ModelAndView modelAndView = new ModelAndView();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("msg", "我接到ajax请求了！！！");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            br.close();
        }
        return null;
    }



}
