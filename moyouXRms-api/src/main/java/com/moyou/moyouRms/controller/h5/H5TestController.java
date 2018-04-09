/**
 */
package com.moyou.moyouRms.controller.h5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moyou.moyouRms.controller.BaseController;

@Controller
@RequestMapping(value = "/h5")
public class H5TestController extends BaseController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView insertTalk() {
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("h5/test");
		return mv;
	}
}

