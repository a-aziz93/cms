package core.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlinx.serialization.json.jsonPrimitive
import java.math.BigDecimal

object BigDecimalSerializer : KSerializer<BigDecimal> {
  override val descriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)


  @OptIn(ExperimentalSerializationApi::class)
  override fun serialize(encoder: Encoder, value: BigDecimal) =
    when (encoder) {
      // use JsonUnquotedLiteral() to encode the BigDecimal literally
      is JsonEncoder -> encoder.encodeJsonElement(JsonUnquotedLiteral(value.toPlainString()))
      else -> encoder.encodeString(value.toPlainString())
    }

  override fun deserialize(decoder: Decoder): BigDecimal =
    when (decoder) {
      // must use decodeJsonElement() to get the value, and then convert it to a BigDecimal
      is JsonDecoder -> decoder.decodeJsonElement().jsonPrimitive.content.toBigDecimal()
      else -> decoder.decodeString().toBigDecimal()
    }
}

typealias BigDecimalJson = @Serializable(with = BigDecimalSerializer::class) BigDecimal