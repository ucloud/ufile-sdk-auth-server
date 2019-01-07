package cn.ucloud.ufile.auth.controller;

import cn.ucloud.ufile.auth.bean.AuthPrivateUrlRequest;
import cn.ucloud.ufile.auth.service.UfileAuthService;
import cn.ucloud.ufile.auth.bean.AuthRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: joshua
 * @E-mail: joshua.yin@ucloud.cn
 * @date: 2018-11-29 10:37
 */
@Controller("authController")
public class AuthController {

    @Autowired
    private UfileAuthService ufileAuthService;

    @RequestMapping(value = "/applyAuth", method = RequestMethod.POST)
    @ResponseBody
    public String applyAuth(@RequestBody AuthRequest requestBean) {
        System.out.println("applyAuth--->\n" + requestBean.toString());
        JsonElement jsonElement = new Gson().fromJson(requestBean.getOptional(), JsonElement.class);
        // 您可以根据您的项目需求，处理端传来的Optional参数集合
        if (jsonElement != null)
            System.out.println(jsonElement.toString());
        return ufileAuthService.calculateAuthroization(requestBean);
    }

    @RequestMapping(value = "/applyPrivateUrlAuth", method = RequestMethod.POST)
    @ResponseBody
    public String applyPrivateUrlAuth(@RequestBody AuthPrivateUrlRequest requestBean) {
        System.out.println("applyPrivateUrlAuth--->\n" + requestBean.toString());
        JsonElement jsonElement = new Gson().fromJson(requestBean.getOptional(), JsonElement.class);
        // 您可以根据您的项目需求，处理端传来的Optional参数集合
        if (jsonElement != null)
            System.out.println(jsonElement.toString());
        return ufileAuthService.calculatePrivateUrlAuthroization(requestBean);
    }
}
