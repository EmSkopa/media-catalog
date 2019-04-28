package com.example.demo.mediaPublish.publish.service;

import com.example.demo.mediaCatalog.dao.MediaCatalogDao;
import com.example.demo.mediaCatalog.model.MediaCatalog;
import com.example.demo.mediaPublish.publish.dao.MediaPublishRepository;
import com.example.demo.mediaPublish.publish.model.MediaPublish;
import com.example.demo.mediaPublish.user.dao.UserRepository;
import com.example.demo.mediaPublish.user.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MediaPublishService {

    private final MediaPublishRepository mediaPublishRepository;
    private final UserRepository userRepository;
    private final MediaCatalogDao mediaCatalogDao;

    public MediaPublishService(MediaPublishRepository mediaPublishRepository,
                               UserRepository userRepository,
                               MediaCatalogDao mediaCatalogDao) {
        this.mediaPublishRepository = mediaPublishRepository;
        this.userRepository = userRepository;
        this.mediaCatalogDao = mediaCatalogDao;
    }

    public int addPublishConnection(MediaPublish mediaPublish){

        List<User> users = new ArrayList<>();
        mediaPublish.getUserIDList().forEach(userID -> {
            users.add(
                    userRepository.findById(userID)
                            .orElse(null));

            if(users.get(users.size()-1) == null) {
                users.remove(users.get(users.size()-1));
                mediaPublish.getUserIDList().remove(userID);
            }
        });

        List<MediaCatalog> mediaCatalog = new ArrayList<>();
        mediaPublish.getMediaIDList().forEach(mediaID -> {
                mediaCatalog.add(
                        mediaCatalogDao.selectMediaById(mediaID)
                                .orElse(null));
                if(mediaCatalog.get(mediaCatalog.size() - 1) == null) {
                    mediaCatalog.remove(mediaCatalog.get(mediaCatalog.size() - 1));
                    mediaPublish.getMediaIDList().remove(mediaID);
                }
        });

        MediaPublish publishMedia = new MediaPublish(   mediaPublish.getId(),
                                                        mediaPublish.getUserIDList(),
                                                        users,
                                                        mediaPublish.getMediaIDList(),
                                                        mediaCatalog,
                                                        mediaPublish.getExpiryPeriod());

        if(publishMedia.getUserList() == null || publishMedia.getMediaCatalogList() == null){ return 1; }

        this.mediaPublishRepository.insert(publishMedia);

        return 0;
    }

    public List<MediaPublish> getExpiredConnections() {
      List<MediaPublish> mediaPublishesExpired = new ArrayList<>();

      this.mediaPublishRepository.findAll().forEach(expiredConnection -> {
          if(expiredConnection.getExpiryPeriod().getYear() <= LocalDate.now().getYear() ||
                  expiredConnection.getExpiryPeriod().getYear() >=
                          LocalDate.now().getYear() &&
                  expiredConnection.getExpiryPeriod().getMonth().getValue() <=
                          LocalDate.now().getMonth().getValue() ||
                  expiredConnection.getExpiryPeriod().getYear() >=
                          LocalDate.now().getYear() &&
                  expiredConnection.getExpiryPeriod().getMonth().getValue() >=
                          LocalDate.now().getMonth().getValue() &&
                  expiredConnection.getExpiryPeriod().getDayOfMonth() <=
                          LocalDate.now().getDayOfMonth()) {

              mediaPublishesExpired.add(expiredConnection);
          }
      });

      return mediaPublishesExpired;
    }

//    public Optional<MediaPublish> getConnectionByID(UUID id){
//        return this.mediaPublishRepository.findById(id);
//    }

    public void deletePublishConnectionByID(UUID id) {
        this.mediaPublishRepository.deleteById(id);
    }

//    public int updatePublishConnectionByID(UUID id, MediaPublish mediaPublish){
//
//        if(this.mediaPublishRepository.findById(id).isEmpty()) {
//            return 0;
//        }
//        else
//        {
//            this.mediaPublishRepository.save(new MediaPublish(id,
//                    mediaPublish.getUserID(),
//                    user, mediaPublish.getMediaID(),
//                    mediaCatalog, mediaPublish.getExpiryPeriod()));
//
//            return 1;
//        }
//    }
}
