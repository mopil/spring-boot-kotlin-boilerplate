package example.boilerplate.logging

import example.boilerplate.utils.getHeadersAsString
import example.boilerplate.utils.logger
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Logging 인터셉터 : 모든 요청에 대한 정보를 기록한다.
 */
class StandardLoggingInterceptor : HandlerInterceptor {
    val log = logger()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.info("{}", getHeadersAsString(request)) // 헤더 정보 찍기
        log.info(
            "-------------- User Request {} -------------- {}: {}",
            request.remoteAddr,
            request.method,
            request.requestURL
        )
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        log.info("{}", getHeadersAsString(response)) // 헤더 정보 찍기
        log.info("------------ Server Response ------------- result : {}", response.status)
        super.postHandle(request, response, handler, modelAndView)
    }
}