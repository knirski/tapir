package sttp.tapir.generic.auto

import magnolia1._
import sttp.tapir.Codec
import sttp.tapir.CodecFormat.TextPlain
import sttp.tapir.generic.internal.ValueClassTextPlainCodecMagnoliaDerivation

package object anyval extends CodecDerivation

trait CodecDerivation extends ValueClassTextPlainCodecMagnoliaDerivation {
  implicit def textPlainCodecForValueClass[T <: AnyVal]: Codec[String, T, TextPlain] = macro Magnolia.gen[T]
}
