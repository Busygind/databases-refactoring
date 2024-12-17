plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "databases-refactoring"
include("payment-mock", "payment-mock-client", "payment-mock-model", "payment-module", "product-management-module")
