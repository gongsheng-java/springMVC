package SpringMVC.blog4gs.util;

import SpringMVC.blog4gs.model.RetInfo;

/**
 * Created by Administrator on 2017/5/21.
 */
public class CommonUtil {
    /**
     * @param args retCode retMsg data
     */
    public static RetInfo setRetInfo(Object ... args){
        RetInfo retInfo = new RetInfo();
        retInfo.setRetCode((String) args[0]);
        retInfo.setRetMsg((String) args[1]);
        if(args.length==3) {
            retInfo.setData(args[2]);
        }
        return retInfo;
    }

}
