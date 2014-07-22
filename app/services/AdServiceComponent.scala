package services

import models.tasks.ui.TaskWithAds
import utils.{AdParser, HttpUtils}

trait AdServiceComponent {
  def adService: AdService

  trait AdService {
    def fetchAds: List[TaskWithAds]
  }
}

trait DefaultAdServiceComponent extends AdServiceComponent {
  override def adService = new DefaultAdService with DefaultTaskProviderComponent

  class DefaultAdService extends AdService {
    this: TaskProviderComponent =>

    override def fetchAds = {
      taskProvider.tasks.map {
        task =>
          val doc = HttpUtils.fetchHtml(task)
          AdParser.parseHtml(task, doc)
      }
    }
  }
}
