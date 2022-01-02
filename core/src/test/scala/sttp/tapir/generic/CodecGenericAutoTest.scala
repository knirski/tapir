package sttp.tapir.generic

import sttp.tapir.{Codec, DecodeResult}
import sttp.tapir.generic.auto.anyval._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sttp.tapir.CodecFormat.TextPlain

class CodecGenericAutoTest extends AnyFlatSpec with Matchers {
  import CodecGenericAutoTest._

  "Codec auto derivation" should "find TextPlain codec for value classes" in {
    val valueClassStringCodec = implicitly[Codec[String, ValueClassString, TextPlain]]
    valueClassStringCodec.decode("test") shouldBe DecodeResult.Value(ValueClassString("test"))
    valueClassStringCodec.encode(ValueClassString("test")) shouldBe "test"

    val valueClassIntCodec = implicitly[Codec[String, ValueClassInt, TextPlain]]
    valueClassIntCodec.decode("2137") shouldBe DecodeResult.Value(ValueClassInt(2137))
    valueClassIntCodec.encode(ValueClassInt(2137)) shouldBe "2137"

    val valueClassBooleanCodec = implicitly[Codec[String, ValueClassBoolean, TextPlain]]
    valueClassBooleanCodec.decode("true") shouldBe DecodeResult.Value(ValueClassBoolean(true))
    valueClassBooleanCodec.encode(ValueClassBoolean(true)) shouldBe "true"
  }

}

object CodecGenericAutoTest {
  case class ValueClassString(value: String) extends AnyVal
  case class ValueClassInt(value: Int) extends AnyVal
  case class ValueClassBoolean(value: Boolean) extends AnyVal
}