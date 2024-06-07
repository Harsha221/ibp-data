package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.validation.Validateable

class VideoGalleryCommand implements Validateable {
      String title
      String youtubeVideoUrl
      Association association
      Boolean active = Boolean.FALSE
      String tags
      static constraints = {
            title nullable: false, blank: false
            youtubeVideoUrl nullable: false, blank: false
      }
}
