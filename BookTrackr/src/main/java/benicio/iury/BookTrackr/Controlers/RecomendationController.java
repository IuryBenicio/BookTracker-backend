package benicio.iury.BookTrackr.Controlers;

import benicio.iury.BookTrackr.Utils.Prompts;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("recomendation")
public class RecomendationController {
    private ChatClient chatClient;

    @Autowired
    Prompts prompts;

    public RecomendationController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/book-sugester")
    public String getRecommendations(@RequestBody List<String> ultimosLivros) throws IOException {
        StringBuilder prompt = new StringBuilder(prompts.loadPrompt("prompt-recomendation"));
        ultimosLivros.forEach(book -> prompt.append(" - ").append(book).append("\n"));

        return chatClient.prompt().user(prompt.toString()).call().content();
    }
}
