package com.akihabara.market.services; 
 
import java.io.FileInputStream; 
import java.net.URI; 
import java.net.http.*; 
import java.util.Properties; 
 
import com.google.gson.*; 
 
public class IAService { 
    private String apiKey; 
 
    public IAService() { 
        try { 
            Properties props = new Properties(); 
            props.load(new FileInputStream("config.properties")); 
            this.apiKey = props.getProperty("openrouter.apiKey"); 
        } catch (Exception e) { 
            System.out.println("Error al cargar la API Key de OpenRouter: " + 
e.getMessage()); 
        } 
    } 
 
    public String generarTexto(String promptUsuario) { 
        try { 
            HttpClient client = HttpClient.newHttpClient(); 
 
            JsonObject message = new JsonObject(); 
            message.addProperty("role", "user"); 
            message.addProperty("content", promptUsuario); 
 
            JsonArray messages = new JsonArray(); 
            messages.add(message); 
 
            JsonObject body = new JsonObject(); 
            body.addProperty("model", "mistralai/mistral-7b-instruct:free"); 
            body.add("messages", messages); 
 
            HttpRequest request = HttpRequest.newBuilder() 
                    .uri(new 
URI("https://openrouter.ai/api/v1/chat/completions")) 
                    .header("Authorization", "Bearer " + apiKey) 
                    .header("Content-Type", "application/json") 
                    
.POST(HttpRequest.BodyPublishers.ofString(body.toString())) 
                    .build(); 
 
            HttpResponse<String> response = client.send(request, 
HttpResponse.BodyHandlers.ofString()); 
 
            JsonObject json = 
JsonParser.parseString(response.body()).getAsJsonObject(); 
            return json.getAsJsonArray("choices") 
                       .get(0) 
                       .getAsJsonObject() 
                       .getAsJsonObject("message") 
                       .get("content") 
                       .getAsString(); 
        } catch (Exception e) { 
            return "Error al generar contenido: " + e.getMessage(); 
        } 
    } 
}