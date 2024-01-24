package digital.sadad.project.core.config.model.plugin.serialization

import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XmlSerializationPolicy
import javax.xml.namespace.QName

data class XMLConfig(
    val repairNamespaces: Boolean? = null,
    val xmlDeclMode: XmlDeclMode? = null,
    val indentString: String? = null,
    val nilAttribute: Pair<QName, String>? = null,
    val xmlVersion: XmlVersion? = null,
    val autoPolymorphic: Boolean? = null,
)
