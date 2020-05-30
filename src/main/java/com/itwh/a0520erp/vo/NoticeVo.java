package com.itwh.a0520erp.vo;

import com.itwh.a0520erp.domain.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author 翁煌
 * @Description:
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NoticeVo extends Notice {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer page=1;
	private Integer limit=10;
	
	private Integer[] ids;//接收多个ID
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
}
