package edu.kh.project.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.project.member.model.vo.Member;

@Component // 스프링이 제어할 수 있도록 bean 등록
@Aspect // 공통 관심사(advice) + 포인트컷이 작성된 클래스임을 명시 (AOP 코드다)
public class BeforeAdviceAspect {

	// 로그 출력 객체 얻어오기
	private Logger logger = LoggerFactory.getLogger(BeforeAdviceAspect.class);
	
	// CommonPoincut 클래스 메서드 중
	// serviceImplPointcut() 메서드에 작성된 pointcut을 얻어옴
	@Before("CommonPointcut.serviceImplPointcut()")
	public void bforeServiceLog(JoinPoint jp) {
		// JoinPoint 객체 : 부가 기능이 적용되는 대상 객체의
		// 클래스명, 메서드명, 파라미터 등을 얻을 수 있음
		
		String str = "-----------------------------------------------------------------------";
		
		// jp.getTarget() : 포인트컷으로 지정된 대상 객체
		String className = jp.getTarget().getClass().getSimpleName();
		
		// jp.getSignature() : 대상이된 메서드
		String methodName = jp.getSignature().getName();
		
		str += "\n[START] : " + className + " - " + methodName +"()\n";
		
		// jp.getArgs() : 메서드에 전달된 매개변수
		str += "[Prameter] : " + Arrays.toString(jp.getArgs()) + "\n";
		
		
		try {
			// 접속자 IP 얻어오기
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			Member loginMember = (Member) req.getSession().getAttribute("loginMember");

			str += "[ip]" + getRemoteAddr(req);
			if (loginMember != null) {
				str += "(email:" + loginMember.getMemberEmail() + ")";
			}
		} catch (Exception e) {
			str += "[스프링 스케쥴러]";
		}
		
		logger.info(str);
	}
	
	
   public String getRemoteAddr(HttpServletRequest request) {

		String ip = null;

		ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }

      return ip;
   }
	
	
	
	
	
	
	
	
	
	
}
