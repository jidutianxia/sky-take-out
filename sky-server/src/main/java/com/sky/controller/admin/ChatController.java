//package com.sky.controller.admin;
//
//
//import dev.langchain4j.community.model.dashscope.QwenChatModel;
//import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.ollama.OllamaChatModel;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/ai/chat")
//@Api("AI")
//@Slf4j
//public class ChatController {
//    @Autowired
//    QwenChatModel qwenChatModel;
//
//    @Autowired
//    ChatLanguageModel deepseekModel;
//
//    @Autowired
//    ChatLanguageModel ollamaChatModel;
//
//    @ApiOperation("千问")
//    @RequestMapping(value = "/qwen",produces = "application/json;charset=UTF-8")
//    public String qwenChatAI(@RequestParam(defaultValue = "你是谁") String message){
//        log.info("用户：{}",message);
//        String chat = qwenChatModel.chat(message);
//        return chat;
//    }
//
//    @ApiOperation("deepseek")
//    @RequestMapping(value = "/deepseek", produces = "application/json;charset=UTF-8")
//    public String deepseekAI(@RequestParam(defaultValue = "你是谁") String message){
//        log.info("用户：{}",message);
//        String chat = deepseekModel.chat(message);
//        return chat;
//    }
//
//    @ApiOperation("ollama")
//    @RequestMapping(value = "/ollama", produces = "application/json;charset=UTF-8")
//    public String ollamaAI(@RequestParam(defaultValue = "你是谁") String message){
//        log.info("用户：{}",message);
//        String chat = ollamaChatModel.chat(message);
//        return chat;
//    }
//}
