plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    // Exclude compatibility stubs that shadow real mod classes at runtime
    exclude("codechicken/**")
    exclude("appeng/me/**")
    exclude("appeng/core/**")
    exclude("appeng/client/**")
    exclude("appeng/container/**")
    exclude("appeng/helpers/**")
    exclude("appeng/items/**")
    exclude("appeng/parts/**")
    exclude("appeng/tile/**")
    exclude("appeng/util/**")
    exclude("net/minecraft/block/properties/**")
    exclude("net/minecraft/block/state/**")
    exclude("net/minecraft/util/math/**")
    exclude("net/minecraftforge/fluids/capability/**")
    exclude("net/minecraftforge/items/**")
    exclude("co/**")
    exclude("com/circulation/**")
    exclude("com/glodblock/**")
    exclude("com/cleanroommc/**")
    exclude("mezz/**")
}
