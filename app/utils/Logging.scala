package utils

import org.slf4j.LoggerFactory

/**
 * Defines `logger` as a lazy value initialized with an underlying `org.slf4j.Logger`
 * named like the class into which this trait is mixed.
 */
trait Logging {
  protected lazy val logger = LoggerFactory getLogger getClass.getName
}