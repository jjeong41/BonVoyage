package ArtBridge.ArtBridgelogin.Controller;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ArtistLoginForm {

    private String id;
    private String pw;
    private boolean isArtist;
}
