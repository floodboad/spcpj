package com.ocean.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends ZuulFilter {
    /**
     * filterType 过滤器类型
     * pre 路由之前
     * routing 路由之时
     * post 路由之后
     * error 发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤顺序，数值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断是否要执行过滤
     * true表示需要执行过滤
     * 此处作为demo演示，设置成false，不令其生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * 过滤的具体逻辑 身份验证 参数校验等
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        获取上下文请求对象
        RequestContext ctx = RequestContext.getCurrentContext();
//        获取request对象
        HttpServletRequest request = ctx.getRequest();
//        获取response对象
        HttpServletResponse response = ctx.getResponse();
//        添加请求头 传递到业务服务
        ctx.addZuulRequestHeader("xxx","xxx");
//        添加响应头，返回给前端
        ctx.addZuulResponseHeader("xxx","xxx");
        return null;
    }
}
