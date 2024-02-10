package ArtBridge.ArtBridgelogin.controller;

import ArtBridge.ArtBridgelogin.domain.ArtistHomepageComment;
import ArtBridge.ArtBridgelogin.service.ArtistHomepageCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/artistHomepageComment")
public class ArtistHomepageCommentController {

    @Autowired
    private ArtistHomepageCommentService artistHomepageCommentService;

    @GetMapping
    public List<ArtistHomepageComment> readAlLArtistHomepageComment() {
        return artistHomepageCommentService.readAllArtistsHomepageComment();
    }

    @GetMapping("/{id}")
    public ArtistHomepageComment readArtistMentionById(@PathVariable Long seq) {
        return artistHomepageCommentService.readOne(seq);
    }

    @PostMapping("/new")
    public ArtistHomepageComment createArtistHomepageComment(@RequestBody ArtistHomepageComment artistHomepageComment) {
        return artistHomepageCommentService.createArtistHomepageComment(artistHomepageComment);
    }

    @PutMapping("/{id}")
    public ArtistHomepageComment updateArtistHomepageComment(@PathVariable Long seq, @RequestBody ArtistHomepageComment updatedArtistHomepageComment) {
        return artistHomepageCommentService.updateArtistHomepageComment(seq, updatedArtistHomepageComment);
    }

    @DeleteMapping("/{id}")
    public void deleteArtistHomepageComment(@PathVariable Long seq) {
        artistHomepageCommentService.deleteArtistHomepageComment(seq);
    }
}
