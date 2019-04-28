package com.example.demo.mediaCatalog.api;

import com.example.demo.mediaCatalog.model.MediaCatalog;
import com.example.demo.mediaCatalog.service.MediaCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/mediacatalog")
@RestController
public class MediaCatalogController {

    private final MediaCatalogService mediaCatalogService;

    @Autowired
    public MediaCatalogController(MediaCatalogService mediacatalogService) {
        this.mediaCatalogService = mediacatalogService;
    }

    @PostMapping
    public void addMedia(@RequestBody MediaCatalog mediaCatalog){
        mediaCatalogService.addMedia(mediaCatalog);
    }

    @GetMapping
    public List<MediaCatalog> getAllMedia() {
        return mediaCatalogService.getAllMedia();
    }

    @GetMapping(path = "{id}")
    public MediaCatalog getMediaById(@PathVariable("id") UUID id) {
        return mediaCatalogService.getMediaById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMediaById(@PathVariable("id")UUID id) {
        mediaCatalogService.deleteMediaById(id);
    }

    @PutMapping(path = "{id}")
    public void updateMediaById(@PathVariable("id")UUID id, @RequestBody MediaCatalog mediaCatalogToUpdate) {
        mediaCatalogService.updateMediaById(id, mediaCatalogToUpdate);
    }

    //@RequestMapping("api/v1/mediacatalog/search")
    @GetMapping(path = "/search/genre")
    public  List<MediaCatalog> searchMediaByGenre(@RequestParam("genre")String genre) {
        return mediaCatalogService.getMediaByGenre(genre);
    }

    @GetMapping(path = "/search/country")
    public  List<MediaCatalog> searchMediaByCountry(@RequestParam("originatingCountry")String country) {
        return mediaCatalogService.getMediaByCountry(country);
    }

    @GetMapping(path = "/search/artist")
    public  List<MediaCatalog> searchMediaByArtistname(@RequestParam("artistName")String artistName) {
        return mediaCatalogService.getMediaByArtistName(artistName);
    }

    @GetMapping(path = "/search/media")
    public  List<MediaCatalog> searchMediaByMediaTitle(@RequestParam("mediaName")String mediaTitle) {
        return mediaCatalogService.getMediaByMediaTitle(mediaTitle);
    }

    @GetMapping(path = "/search/publisher")
    public  List<MediaCatalog> searchMediaByPublisher(@RequestParam("publisher")String publisher) {
        return mediaCatalogService.getMediaByPublisher(publisher);
    }

    @GetMapping(path = "/search/filter")
    public List<MediaCatalog> searchMedia(@RequestParam(value = "genre", required = false) String genre,
                                          @RequestParam(value = "originatingCountry", required = false)String country,
                                          @RequestParam(value = "artistName", required = false)String artistName,
                                          @RequestParam(value = "mediaName", required = false)String mediaTitle,
                                          @RequestParam(value = "publisher", required = false)String publisher) {

        return mediaCatalogService.getSearch(genre,country,artistName,mediaTitle,publisher);
    }
}
