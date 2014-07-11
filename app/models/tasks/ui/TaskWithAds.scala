package models.tasks.ui

import models.classifieds.Ad
import models.classifieds.details.Car
import models.tasks.Task

case class TaskWithAds(task: Task, ads: Seq[Ad[Car]])
