package com.example.demo.mediaCatalog.api;

import com.example.demo.mediaCatalog.model.MediaCatalog;
import com.example.demo.mediaCatalog.service.MediaCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping(path = "/search/genre")
    public  List<MediaCatalog> searchMediaByGenre(@RequestParam("genre") Optional<String> genre,
                                                  @RequestParam("sort") Optional<String> sort,
                                                  @RequestParam("dir")Optional<Integer> dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if(dir.orElse(0) == -1){
            direction = Sort.Direction.DESC;
        }

        return mediaCatalogService.getMediaByGenre(genre.orElse(""),
                Sort.by(direction, sort.orElse("id")));
    }

    @GetMapping(path = "/search/country")
    public  List<MediaCatalog> searchMediaByCountry(@RequestParam("originatingCountry")Optional<String> country,
                                                    @RequestParam("sort") Optional<String> sort,
                                                    @RequestParam("dir")Optional<Integer> dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if(dir.orElse(0) == -1){
            direction = Sort.Direction.DESC;
        }

        return mediaCatalogService.getMediaByCountry(country.orElse(""),
                Sort.by(direction, sort.orElse("id")));
    }

    @GetMapping(path = "/search/artist")
    public  List<MediaCatalog> searchMediaByArtistname(@RequestParam("artistName")Optional<String> artistName,
                                                       @RequestParam("sort")Optional<String> sort,
                                                       @RequestParam("dir")Optional<Integer> dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if(dir.orElse(0) == -1){
            direction = Sort.Direction.DESC;
        }

        return mediaCatalogService.getMediaByArtistName(artistName.orElse(""),
                Sort.by(direction, sort.orElse("id")));
    }

    @GetMapping(path = "/search/media")
    public  List<MediaCatalog> searchMediaByMediaTitle(@RequestParam("mediaName")Optional<String> mediaTitle,
                                                       @RequestParam("sort")Optional<String> sort,
                                                       @RequestParam("dir")Optional<Integer> dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if(dir.orElse(0) == -1){
            direction = Sort.Direction.DESC;
        }

        return mediaCatalogService.getMediaByMediaTitle(mediaTitle.orElse(""),
                Sort.by(direction, sort.orElse("id")));
    }

    @GetMapping(path = "/search/publisher")
    public  List<MediaCatalog> searchMediaByPublisher(@RequestParam("publisher")Optional<String> publisher,
                                                      @RequestParam("sort")Optional<String> sort,
                                                      @RequestParam("dir")Optional<Integer> dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if(dir.orElse(0) == -1){
            direction = Sort.Direction.DESC;
        }

        return mediaCatalogService.getMediaByPublisher(publisher.orElse(""),
                Sort.by(direction, sort.orElse("id")));
    }

    @GetMapping(path = "/search/filter")
    public List<MediaCatalog> searchMedia(@RequestParam(value = "genre", required = false) Optional<String> genre,
                                          @RequestParam(value = "originatingCountry", required = false)Optional<String> country,
                                          @RequestParam(value = "artistName", required = false)Optional<String> artistName,
                                          @RequestParam(value = "mediaName", required = false)Optional<String> mediaTitle,
                                          @RequestParam(value = "publisher", required = false)Optional<String> publisher,
                                          @RequestParam(value = "sort", required = false)Optional<String> sort,
                                          @RequestParam(value = "dir", required = false)Optional<Integer> dir) {

        return mediaCatalogService.getSearch(genre.orElse(""),
                country.orElse(null),
                artistName.orElse(""),
                mediaTitle.orElse(""),
                publisher.orElse(null),
                sort.orElse("id"),
                dir.orElse(0));
    }
}
