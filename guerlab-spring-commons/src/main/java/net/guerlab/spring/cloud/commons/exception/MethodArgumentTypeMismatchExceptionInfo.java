package net.guerlab.spring.cloud.commons.exception;

import org.springframework.util.ClassUtils;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author guer
 *
 */
public class MethodArgumentTypeMismatchExceptionInfo extends AbstractI18nInfo {

    private Object value;

    private Class<?> requiredType;

    public MethodArgumentTypeMismatchExceptionInfo(MethodArgumentTypeMismatchException cause) {
        super(cause, 403);
        value = cause.getValue();
        requiredType = cause.getRequiredType();
    }

    @Override
    protected String getKey() {
        return requiredType == null ? Keys.METHOD_ARGUMENT_TYPE_MISMATCH_WITHOUT_TYPE
                : Keys.METHOD_ARGUMENT_TYPE_MISMATCH;
    }

    @Override
    protected Object[] getArgs() {
        if (requiredType == null) {
            return new Object[] {
                    ClassUtils.getDescriptiveType(value),
            };
        } else {
            return new Object[] {
                    ClassUtils.getDescriptiveType(value), ClassUtils.getQualifiedName(requiredType)
            };
        }
    }

}