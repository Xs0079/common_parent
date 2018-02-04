package com.youxinpai.common.util.log.log4j2.layout.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.youxinpai.common.util.log.log4j2.layout.json.vo.JsonLogVO;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;

/**
 * 目的：兼容json1的插件，提供统一标准的json输出
 * 自定义jsonLayout ，输出格式按照log4j1的json插件完全匹配的格式进行json转换
 * log4j1 -> json插件：
 * <groupId>net.logstash.log4j</groupId>
 * <artifactId>jsonevent-layout</artifactId>
 * <version>1.7</version>
 * Created by Xs on 2018/1/4.
 */
@Plugin(name = "log4j1JsonLayout", category = "Core", elementType = "layout", printObject = true)
public class log4j1JsonLayout extends AbstractStringLayout {
    private static final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    @PluginFactory
    public static log4j1JsonLayout createLayout() {
        return new log4j1JsonLayout(Charset.defaultCharset());
    }

    protected log4j1JsonLayout(Charset charset) {
        super(charset);
    }

    @Override
    public String toSerializable(LogEvent logEvent) {
        String json = "";

        try {
            json = mapper.writeValueAsString(JsonLogVO.getLogMessageBean(logEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("自定义jsonLayout 将logEvent转换json时出现异常...");
        }
        return json;
    }
}
