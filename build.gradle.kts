plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    // Only exclude stubs that shadow real mod classes at runtime
    // 1.8+/1.12.2 compat stubs (net/minecraft/*, net/minecraftforge/*) are kept — needed at runtime
    exclude("codechicken/**")
    exclude("co/**")
    exclude("com/circulation/**")
    exclude("com/cleanroommc/**")
    exclude("mezz/**")
}
