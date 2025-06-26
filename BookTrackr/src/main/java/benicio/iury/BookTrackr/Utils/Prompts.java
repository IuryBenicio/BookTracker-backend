package benicio.iury.BookTrackr.Utils;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Prompts {

    public String loadPrompt(String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();

        try(var inputStream = classLoader.getResourceAsStream(fileName + ".txt")){
            if (inputStream == null){
                throw  new IOException("Arquivo de prompt não encontrado");
            }
            return new String(inputStream.readAllBytes());
        }
    }
}
