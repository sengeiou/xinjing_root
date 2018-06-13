package cn.easy.xinjing.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easy.base.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.easy.base.bean.PageBean;
import cn.easy.base.bean.common.AjaxResultBean;
import cn.easy.xinjing.domain.CapitalFlow;
import cn.easy.xinjing.service.CapitalFlowService;

@Controller
@RequestMapping("/capitalFlow")
public class CapitalFlowController extends BaseController {
    @Autowired
    private CapitalFlowService	capitalFlowService;

    @RequestMapping("")
    String index(Model model, HttpServletRequest request) {
        increment("web.capitalFlow.index");
        return "capitalFlow/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    Page<CapitalFlow> list(PageBean pageBean, HttpServletRequest request) {
        increment("web.capitalFlow.list");

        Page<CapitalFlow> page = capitalFlowService.search(searchParams(request), pageBean);
        return page;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    String formGet(@RequestParam(value = "id", required = false) String id, Model model, HttpServletRequest request) {
        increment("web.capitalFlow.formGet");
        if (isValidId(id)) {
            CapitalFlow capitalFlow = capitalFlowService.getOne(id);
            model.addAttribute("capitalFlow", capitalFlow);
        } else {
            model.addAttribute("capitalFlow", new CapitalFlow());
        }
        return "capitalFlow/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    @ResponseBody
    AjaxResultBean formPost(CapitalFlow capitalFlow, HttpServletRequest request) {
        increment("web.capitalFlow.formPost");
        capitalFlowService.save(capitalFlow);
        return toSuccess("保存成功");
    }

    @RequestMapping("/delete")
    @ResponseBody
    AjaxResultBean delete(@RequestParam(value = "id") String id) {
        increment("web.capitalFlow.delete");
        capitalFlowService.delete(id);
        return toSuccess("删除成功");
    }

}


