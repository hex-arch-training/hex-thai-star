package io.github.hexarchtraining.hts.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.hexarchtraining.hts.archunit.architecture.HexArchitecture;

@AnalyzeClasses(
    packages = "io.github.hexarchtraining.hts",
    importOptions = ImportOption.DoNotIncludeTests.class
)
class HexArchitectureTests {

    public static HexArchitecture htsHexArchitecture(String boundaryContext) {
        return HexArchitecture.hexArchitecture()
            .domainModels("io.github.hexarchtraining.hts." + boundaryContext + ".domain..")
            .apiIn("io.github.hexarchtraining.hts." + boundaryContext + ".port.in..")
            .apiOut("io.github.hexarchtraining.hts." + boundaryContext + ".port.out..")
            .applicationServices("io.github.hexarchtraining.hts." + boundaryContext + ".application.service..")
            .adapterIn("io.github.hexarchtraining.hts." + boundaryContext + ".adapter.in..")
            .adapterOut("io.github.hexarchtraining.hts." + boundaryContext + ".adapter.out..")
            .configuration("io.github.hexarchtraining.hts.configuration..")
            .withOptionalLayers(true);
    }

    @ArchTest
    static final ArchRule hexArchitectureIsRespectedInBooking = htsHexArchitecture("booking")
        .externalAdapterOut("io.github.hexarchtraining.hts.order.adapter.out..");

    @ArchTest
    static final ArchRule hexArchitectureIsRespectedInOrder = htsHexArchitecture("order");

}
