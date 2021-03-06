package net.guerlab.spring.swagger2.cloud;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import net.guerlab.commons.collection.CollectionUtil;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 文档配置
 *
 * @author guer
 *
 */
@Primary
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class DocumentationConfig implements SwaggerResourcesProvider {

    @Autowired
    private SwaggerProperties properties;

    @Override
    public List<SwaggerResource> get() {
        Map<String, SwaggerResourceBuild> resources = properties.getResources();

        if (resources == null || resources.isEmpty()) {
            return Collections.emptyList();
        }

        return CollectionUtil.toList(resources.entrySet(), entry -> {
            String key = entry.getKey();
            SwaggerResourceBuild build = entry.getValue();

            if (StringUtils.isBlank(build.getName())) {
                build.setName(key);
            }
            if (StringUtils.isBlank(build.getPath())) {
                build.setPath(key);
            }

            return build.build();
        });
    }

}
