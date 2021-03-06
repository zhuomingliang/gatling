/*
 * Copyright 2011-2018 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.http.action.ws2

import scala.collection.mutable

import io.gatling.commons.validation.Validation
import io.gatling.core.check.{ Check, CheckResult }
import io.gatling.core.session.Session

sealed trait WsCheck
case class WsTextCheck(wrapped: Check[String]) extends WsCheck with Check[String] {
  override def check(message: String, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] =
    wrapped.check(message, session)
}
case class WsBinaryCheck(wrapped: Check[Array[Byte]]) extends WsCheck with Check[Array[Byte]] {
  override def check(message: Array[Byte], session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] =
    wrapped.check(message, session)
}
