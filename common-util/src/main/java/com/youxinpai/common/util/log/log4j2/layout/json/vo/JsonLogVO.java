package com.youxinpai.common.util.log.log4j2.layout.json.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.core.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 对应log4j1 插件中的 json实体
 */
public class JsonLogVO {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS");
    private static final Logger logger = LoggerFactory.getLogger(JsonLogVO.class);
    public static final String AT = " at ";
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static String hostName = "";

    static {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @JsonIgnore
    private long timeMillis;

    /**
     * 时间格式 参考df
     */
    @JsonProperty("@timestamp")
    private String logDateTime;

    @JsonProperty("source_host")
    private String sourceHost;

    private String file;

    private String method;

    private String level;

    @JsonProperty("line_number")
    private String lineNumber;

    private String threadName;

    @JsonProperty("logger_name")
    private String loggerName;

    private String message;

    @JsonProperty("class")
    private String className;

    private ExceptionMessage exception;


    public static JsonLogVO getLogMessageBean(LogEvent logEvent) {
        StackTraceElement source = logEvent.getSource();
        JsonLogVO jsonLogVO = new JsonLogVO();

        jsonLogVO.setTimeMillis(logEvent.getTimeMillis());
        jsonLogVO.setLogDateTime(df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonLogVO.getTimeMillis()), ZoneId.systemDefault())));
        jsonLogVO.setSourceHost(hostName);
        jsonLogVO.setFile(source.getFileName());
        jsonLogVO.setClassName(source.getClassName());
        jsonLogVO.setMethod(source.getMethodName());
        jsonLogVO.setLineNumber("" + source.getLineNumber());
        jsonLogVO.setLevel("" + logEvent.getLevel());
        jsonLogVO.setThreadName(logEvent.getThreadName());
        jsonLogVO.setLoggerName(logEvent.getLoggerName());
        jsonLogVO.setMessage(logEvent.getMessage().getFormattedMessage());

        if (logEvent.getThrown() != null) {
            ExceptionMessage exceptionMessage = new ExceptionMessage(logEvent);
            jsonLogVO.setException(exceptionMessage);
        }

        return jsonLogVO;
    }


    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public String getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(String logDateTime) {
        this.logDateTime = logDateTime;
    }

    public String getSourceHost() {
        return sourceHost;
    }

    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ExceptionMessage getException() {
        return exception;
    }

    public void setException(ExceptionMessage exception) {
        this.exception = exception;
    }
}

/**
 * 异常描述对象
 */
class ExceptionMessage {
    private String stacktrace;
    @JsonProperty("exception_class")
    private String exceptionClass;
    @JsonProperty("exception_message")
    private String exceptionMessage;

    public ExceptionMessage(LogEvent logEvent) {
        this.exceptionClass = logEvent.getThrown().getClass().getCanonicalName();
        this.exceptionMessage = logEvent.getThrown().getMessage();

        if (logEvent.getThrown().getStackTrace() != null) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(logEvent.getThrown().getStackTrace()).forEach(stackTraceElement -> {
                sb.append(JsonLogVO.AT);
                sb.append(stackTraceElement);
                sb.append(JsonLogVO.LINE_SEPARATOR);
            });

            this.stacktrace = sb.toString();
        }
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}