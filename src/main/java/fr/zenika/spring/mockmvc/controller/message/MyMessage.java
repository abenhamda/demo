package fr.zenika.spring.mockmvc.controller.message;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;

/**
 * Http message
 */
public class MyMessage implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -455801360818448640L;

    /**
     * Message value
     */
    private String value;

    private boolean created;

    public MyMessage() {
    }

    public MyMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    /**
     * Default {@code toString} including all object properties.
     */
    @Override
    public final String toString() {
        return ReflectionToStringBuilder.toStringExclude(this);
    }

    /**
     * Default {@code hashCode} using all object properties.
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Default {@code equals} comparing all object properties.
     *
     * @param obj
     *         The compared object.
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
