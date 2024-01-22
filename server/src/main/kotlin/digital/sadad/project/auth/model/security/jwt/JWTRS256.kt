package digital.sadad.project.auth.model.security.jwt

import auth.entity.user.UserEntity
import com.auth0.jwk.JwkProvider
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.algorithms.Algorithm
import digital.sadad.project.core.config.model.security.jwt.JWTRS256Config
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import java.util.concurrent.TimeUnit

class JWTRS256(
    val config: JWTRS256Config,
) : JWT(config) {

    val jwkProvider: JwkProvider by lazy {
        JwkProviderBuilder(config.issuer)
            .cached(10, 24, TimeUnit.HOURS)
            .rateLimited(10, 1, TimeUnit.MINUTES)
            .build()
    }

    fun create(
        userEntity: UserEntity,
        roles: List<String>? = null,
    ): String {
        val publicKey = jwkProvider.get("6f8856ed-9189-488f-9011-0ff4b6c08edc").publicKey
        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(config.privateKey))
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpecPKCS8)

        return create(userEntity, roles, Algorithm.RSA256(publicKey as RSAPublicKey, privateKey as RSAPrivateKey))
    }
}