package dev.rayzr.gameboi.translation.bundle

import org.yaml.snakeyaml.Yaml
import java.util.*

/**
 * A control for YAML resource bundles.
 */
class YamlResourceControl : ResourceBundle.Control() {

    override fun getFormats(baseName: String?): List<String> {
        return listOf("yml", "yaml")
    }

    override fun newBundle(
            baseName: String,
            locale: Locale,
            format: String,
            loader: ClassLoader,
            reload: Boolean
    ): ResourceBundle? {
        if (format != "yaml" && format != "yml") {
            return null
        }

        val bundleName = toBundleName(baseName.removeSuffix(".yaml").removeSuffix(".yml"), locale)
        val resourceName = toResourceName(bundleName, format)
        val fileUrl = loader.getResource(resourceName) ?: return null
        val connection = fileUrl.openConnection()
        if (reload) {
            connection.useCaches = false
        }
        val values = Yaml().load<Map<String, Any>>(connection.getInputStream())

        return YamlResourceBundle(values)
    }

    private class YamlResourceBundle(private val values: Map<String, Any>) : ResourceBundle() {
        override fun getKeys(): Enumeration<String> {
            return Collections.enumeration(values.keys)
        }

        override fun handleGetObject(key: String): Any? {
            return handleGet(key, values)
        }

        private fun handleGet(key: String, root: Map<String, Any>): Any? {
            if ("." !in key) {
                return root[key]
            }
            val name = key.substringBefore('.')
            val rest = key.substringAfter('.')
            val subMap = root[name] as? Map<*, *> ?: return null

            @Suppress("UNCHECKED_CAST")
            return handleGet(rest, subMap as Map<String, Any>)
        }
    }
}