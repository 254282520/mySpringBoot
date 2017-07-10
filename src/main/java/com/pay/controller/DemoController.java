/**   
* @Title: DemoController.java 
* @Package com.pay.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author （作者）  
* @date 2017年7月6日 上午9:51:47 
* @version V1.0   
*/
package com.pay.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DemoController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author (作者)
 * @date 2017年7月6日 上午9:51:47
 * @version V1.0
 */

@RestController
@RequestMapping("/index")
public class DemoController

{

    @RequestMapping("/page")
    public String indexPage(ModelMap model)
    {
        model.addAttribute("hello", "13212");
        return "hello";

    }

}
