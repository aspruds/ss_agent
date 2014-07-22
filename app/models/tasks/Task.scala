package models.tasks

import models.criteria.Criteria


abstract class Task(thePath: String, theName: String) {
  def path: String = "/lv/transport/cars/" + thePath + "/search-result/"

  def baseUrl: String = "http://www.ss.lv"

  def fullUrl: String = baseUrl + path

  def criterias: List[Criteria]

  def name: String = theName

  override def toString = "[Task: url=" + path + " name=" + theName + "]"

  override def clone = this
}
