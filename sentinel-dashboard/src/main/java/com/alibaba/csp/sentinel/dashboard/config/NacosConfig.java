/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.config;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.FlowRuleNacosProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.FlowRuleNacosPublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class NacosConfig {

    @Value("${nacos.addr}")
    private String nacosAddr;
    @Value("${nacos.namespace}")
    private String nacosNamespace;

    @Bean("nacosConfigService")
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        //nacos集群地址
        properties.put(PropertyKeyConst.SERVER_ADDR,nacosAddr);
        //namespace为空即为public
        properties.put(PropertyKeyConst.NAMESPACE,nacosNamespace);
        return ConfigFactory.createConfigService(properties);
    }


    /***************************************流控规则*************************************/
    @Bean("flowRuleEntityEncoder")
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean("flowRuleEntityDecoder")
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    /***************************************熔断降级规则*************************************/
    @Bean("degradeRuleEntityEncoder")
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean("degradeRuleEntityDecoder")
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }


    /***************************************系统规则*************************************/
    @Bean("systemRuleEntityEncoder")
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean("systemRuleEntityDecoder")
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }


    /***************************************授权规则*************************************/
    @Bean("authorityRuleEntityEncoder")
    public Converter<List<AuthorityRuleEntity>, String> authorityRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean("authorityRuleEntityDecoder")
    public Converter<String, List<AuthorityRuleEntity>> authorityRuleEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }


    /***************************************参数规则*************************************/
    @Bean("paramFlowRuleEntityEncoder")
    public Converter<List<ParamFlowRuleEntity>, String> paramFlowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean("paramFlowRuleEntityDecoder")
    public Converter<String, List<ParamFlowRuleEntity>> paramFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }

}
