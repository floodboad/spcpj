package com.ocean.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthPreFilter extends ZuulFilter {

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
     * 这里进行实际的路径区分判断
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());

        if(request.getRequestURI().contains("api-view")){
            return true;
        }
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

        String token = request.getHeader("token");
        if(token == null || token.equals("")){
            ctx.setSendZuulResponse(false);
//            防止中文乱码
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setResponseBody("userToken is null");
        }
//        获取response对象
        HttpServletResponse response = ctx.getResponse();
//        添加请求头 传递到业务服务
        ctx.addZuulRequestHeader("xxx","xxx");
//        添加响应头，返回给前端
        ctx.addZuulResponseHeader("xxx","xxx");
        return null;
    }
}
