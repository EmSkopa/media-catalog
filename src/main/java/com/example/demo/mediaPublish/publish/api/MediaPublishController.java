package com.example.demo.mediaPublish.publish.api;

import com.example.demo.mediaPublish.publish.model.MediaPublish;
import com.example.demo.mediaPublish.publish.service.MediaPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/publish")
@RestController
public class MediaPublishController {

    private final MediaPublishService mediaPublishService;

    @Autowired
    public MediaPublishController(MediaPublishService mediaPublishService) {
        this.mediaPublishService = mediaPublishService;
    }

    @PostMapping
    public int addMediaPublish(@RequestBody MediaPublish mediaPublish) {
        return mediaPublishService.addPublishConnection(mediaPublish);
    }

    @GetMapping
    public List<MediaPublish> getAllExpired() {
        return mediaPublishService.getExpiredConnections();
    }

    @DeleteMapping(path = "{id}")
    public void deleteMediaPublishByID(@PathVariable("id") UUID id) {
        mediaPublishService.deletePublishConnectionByID(id);
    }

}
