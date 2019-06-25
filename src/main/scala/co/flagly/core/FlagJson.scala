package co.flagly.core

import java.util.UUID

import co.flagly.utils.ZDT
import play.api.libs.json._

import scala.util.Try

object FlagJson {
  implicit val flagReads: Reads[Flag] =
    Reads[Flag] {
      case json: JsObject =>
        val maybeFlag = for {
          id            <- (json \ "id").asOpt[UUID]
          applicationId <- (json \ "applicationId").asOpt[UUID]
          name          <- (json \ "name").asOpt[String]
          description   <- (json \ "description").asOpt[String]
          value         <- (json \ "value").asOpt[Boolean]
          createdAt     <- (json \ "createdAt").asOpt[String].flatMap(zdt => Try(ZDT.fromString(zdt)).toOption)
          updatedAt     <- (json \ "updatedAt").asOpt[String].flatMap(zdt => Try(ZDT.fromString(zdt)).toOption)
        } yield {
          Flag.of(id, applicationId, name, description, value, createdAt, updatedAt)
        }

        maybeFlag match {
          case None       => JsError(s"$json is not a valid Flag!")
          case Some(flag) => JsSuccess(flag)
        }

      case json =>
        JsError(s"$json is not a valid Flag!")
    }

  implicit val flagWrites: Writes[Flag] =
    Writes[Flag] { flag =>
      Json.obj(
        "id"            -> flag.id,
        "applicationId" -> flag.applicationId,
        "name"          -> flag.name,
        "description"   -> flag.description,
        "value"         -> flag.value,
        "createdAt"     -> ZDT.toString(flag.createdAt),
        "updatedAt"     -> ZDT.toString(flag.updatedAt)
      )
    }
}
