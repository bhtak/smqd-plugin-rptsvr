package com.thing2x.rptsvr

import com.thing2x.smqd.Smqd

import scala.concurrent.Future

object Repository {
  def findInstance(smqd: Smqd): Repository = {
    val repositoryClass = classOf[Repository]
    smqd.pluginManager.pluginDefinitions.find{ pd =>
      repositoryClass.isAssignableFrom(pd.clazz)
    }.map(_.instances.head.instance.asInstanceOf[Repository]).get
  }

  class RepositoryException extends Exception

  class ResourceNotFoundException(uri: String) extends Exception
  class ResourceAlreadyExistsExeption(uri: String) extends Exception
}

trait Repository {
  val context: RepositoryContext
  def listFolder(path: String, recursive: Boolean, sortBy: String, limit: Int): Future[ListResult[Resource]]

  def setResource(path: String, request: Resource, createFolders: Boolean, overwrite: Boolean): Future[Result[Resource]]
  def getResource(path: String, isReferenced: Boolean = false): Future[Result[Resource]]

  def getContent(path: String): Future[Either[Throwable, FileContent]]

  def deleteResource(path: String): Future[Boolean]
}
