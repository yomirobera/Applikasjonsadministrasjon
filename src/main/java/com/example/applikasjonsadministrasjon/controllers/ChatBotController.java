package com.example.applikasjonsadministrasjon.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.cdimascio.dotenv.Dotenv;



@RestController
@RequestMapping(path = "api/v1/")
public class ChatBotController {
    

    @PostMapping("/ask")
    public ResponseEntity<String> askChatBot(@RequestBody String userMessage){
        String chatBotResponse= sendToOpenAI(userMessage);
        return ResponseEntity.ok(chatBotResponse);
    }

    @Autowired
private RestTemplate restTemplate;


private String openaiApiKey = Dotenv.load().get("OPENAI_API_KEY");

private String sendToOpenAI(String userMessageInput) {
    String openAiUrl = "https://api.openai.com/v1/chat/completions";  // URL may change, refer to OpenAI documentation
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + openaiApiKey);

    
     JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");

        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", userMessageInput); // message from the user

        JSONArray messagesArray = new JSONArray();
        messagesArray.put(systemMessage);
        messagesArray.put(userMessage);

        JSONObject body = new JSONObject();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", messagesArray);

    HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);

    ResponseEntity<String> response = restTemplate.postForEntity(openAiUrl, entity, String.class);

    // Extract and return the chatbot's response from the API's response.
    JSONObject responseBody = new JSONObject(response.getBody());
    String content = responseBody.getJSONArray("choices")
    .getJSONObject(0)
    .getJSONObject("message")
    .getString("content")
    .trim();
    JSONObject contentObject = new JSONObject(content);
    String assistantMessage = contentObject.getString("assistantMessage");

    return assistantMessage;

}
}
