package dev.rayzr.gameboi.translation.bundle

import org.kohsuke.MetaInfServices
import java.util.*
import java.util.spi.ResourceBundleControlProvider

/**
 * A provider for resource bundle controls serving [YamlResourceControl]s.
 */
@MetaInfServices(ResourceBundleControlProvider::class)
class YamlResourceBundleControlProvider : ResourceBundleControlProvider {

    companion object {
        private val YAML_CONTROL = YamlResourceControl()
    }

    override fun getControl(baseName: String): ResourceBundle.Control? {
        if (baseName.endsWith(".yaml") || baseName.endsWith(".yml")) {
            return YAML_CONTROL
        }
        return null
    }
}