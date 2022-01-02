package sttp.tapir.generic.internal

import magnolia1._
import sttp.tapir.Codec
import sttp.tapir.CodecFormat.TextPlain
import sttp.tapir.DecodeResult
import sttp.tapir.Schema

trait ValueClassTextPlainCodecMagnoliaDerivation {

  type Typeclass[T] = Codec[String, T, TextPlain]

  def join[T <: AnyVal](valueClass: CaseClass[Typeclass, T]): Typeclass[T] = new Codec[String, T, TextPlain] {

    override def rawDecode(l: String): DecodeResult[T] = {
      valueClass.parameters.head.typeclass.decode(l).map(value => valueClass.rawConstruct(Seq(value)))
    }

    override def encode(h: T): String = {
      val onlyParam = valueClass.parameters.head
      onlyParam.typeclass.encode(onlyParam.dereference(h))
    }

    override def schema: Schema[T] = Schema.string[T]

    override def format: TextPlain = TextPlain()
  }

}
