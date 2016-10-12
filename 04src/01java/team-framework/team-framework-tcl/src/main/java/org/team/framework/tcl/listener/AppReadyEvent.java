package org.team.framework.tcl.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * <DL>
 * <DD>应用启动完成可扩展事件.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class AppReadyEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4942520404303535961L;
	
	/**
	 * 
	 * <DL>
	 * <DD>默认无参构造函数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月10日
	 */
	public AppReadyEvent() {
		super(true);
	}

}
