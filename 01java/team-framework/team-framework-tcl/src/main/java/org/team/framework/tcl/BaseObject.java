package org.team.framework.tcl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public abstract class BaseObject implements Serializable {
    private static final long serialVersionUID = 1305410444822715269L;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE).toString();
    }
}
