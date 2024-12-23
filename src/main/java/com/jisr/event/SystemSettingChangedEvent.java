package com.jisr.event;

import org.springframework.context.ApplicationEvent;
import com.jisr.entity.SystemSetting;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemSettingChangedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6855838873092346618L;

	private final SystemSetting systemSetting;

	public SystemSettingChangedEvent(Object source, SystemSetting systemSetting) {
		super(source);
		this.systemSetting = systemSetting;
	}

}
