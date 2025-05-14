//package com.sky.config;
//
//import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.openai.OpenAiChatModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AiModelConfig {
//    @Value("${langchain4j.community.deepseek.chat-model.api-key}")
//    private String deepseekApiKey;
//
//    @Value("${langchain4j.community.deepseek.chat-model.modelName}")
//    private String deepseekModelName;
//
//    @Value("${langchain4j.community.deepseek.chat-model.baseUrl}")
//    private String baseUrl;
//
//    @Value("${langchain4j.community.deepseek.chat-model.temperature:0.7}")
//    private Double temperature;
//
//    @Value("${langchain4j.community.deepseek.chat-model.topP:0.9}")
//    private Double topP;
//
//    @Value("${langchain4j.community.deepseek.chat-model.maxTokens:1024}")
//    private Integer maxTokens;
//
//
//
//
//
//
//    @Bean
//    public ChatLanguageModel deepseekModel() {
//        return OpenAiChatModel.builder()
//                .apiKey(deepseekApiKey)
//                .modelName(deepseekModelName)
//                .baseUrl(baseUrl)
//                .temperature(temperature)
//                .topP(topP)
//                .maxTokens(maxTokens)
//                .build();
//    }
//}
