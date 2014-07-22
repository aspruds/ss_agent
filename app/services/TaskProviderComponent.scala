package services

import models.tasks.Task
import models.tasks.transport.cars._

trait TaskProviderComponent {
  def taskProvider: TaskProvider

  trait TaskProvider {
    def tasks: List[Task]
  }
}

trait DefaultTaskProviderComponent extends TaskProviderComponent {
  override val taskProvider = new DefaultTaskProvider

  class DefaultTaskProvider extends TaskProvider {
    override def tasks = List(
        Saab93Petrol(),
        Saab93Diesel(),
        Saab95Petrol(),
        Saab95LPG(),
        VolvoV40Petrol(),
        VolvoV50()
      )
  }
}
