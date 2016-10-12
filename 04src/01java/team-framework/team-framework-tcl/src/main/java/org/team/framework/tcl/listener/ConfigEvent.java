package org.team.framework.tcl.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * <DL>
 * <DD>资源配置事件.</DD><BR>
 * </DL>
 * @author Team <liul>
 * @date 2016年3月10日
 * 修改记录:
 * 初始化
 */
public class ConfigEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * <DL>
	 * <DD>默认无参构造函数.</DD><BR>
	 * </DL>
	 * @author Team <liul>
	 * @date 2016年3月10日
	 */
	public ConfigEvent() {
		super(true);
	}

}
