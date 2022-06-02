package io.github.hexarchtraining.hts.archunit.architecture;

import com.google.common.base.Joiner;
import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.base.Optional;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.library.Architectures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static java.lang.System.lineSeparator;

public class HexArchitecture implements ArchRule {

    @PublicAPI(usage = ACCESS)
    public static HexArchitecture hexArchitecture() {
        return new HexArchitecture();
    }

    private static final String DOMAIN_MODEL_LAYER = "domain model";
    private static final String API_IN_LAYER = "api in";
    private static final String API_OUT_LAYER = "api out";
    private static final String APPLICATION_SERVICE_LAYER = "application service";
    private static final String ADAPTER_IN_LAYER = "adapter in";
    private static final String ADAPTER_OUT_LAYER = "adapter out";
    private static final String EXTERNAL_ADAPTER_OUT_LAYER = "external adapter out";
    private static final String CONFIGURATION_LAYER = "configuration";

    private final Optional<String> overriddenDescription;
    private String[] domainModelPackageIdentifiers = new String[0];
    private String[] apiInPackageIdentifiers = new String[0];
    private String[] apiOutPackageIdentifiers = new String[0];
    private String[] applicationPackageIdentifiers = new String[0];
    private String[] adapterInPackageIdentifiers = new String[0];
    private String[] adapterOutPackageIdentifiers = new String[0];
    private String[] externalAdapterOutPackageIdentifiers = new String[0];
    private String[] configurationPackageIdentifiers = new String[0];
    private boolean optionalLayers = false;
    private List<HexArchitecture.IgnoredDependency> ignoredDependencies = new ArrayList<>();

    private HexArchitecture() {
        overriddenDescription = Optional.empty();
    }

    private HexArchitecture(String[] domainModelPackageIdentifiers,
                            String[] apiInPackageIdentifiers,
                            String[] apiOutPackageIdentifiers,
                            String[] applicationPackageIdentifiers,
                            String[] adapterInPackageIdentifiers,
                            String[] adapterOutPackageIdentifiers,
                            String[] externalAdapterOutPackageIdentifiers,
                            String[] configurationPackageIdentifiers,
                            List<HexArchitecture.IgnoredDependency> ignoredDependencies,
                            Optional<String> overriddenDescription) {
        this.domainModelPackageIdentifiers = domainModelPackageIdentifiers;
        this.apiInPackageIdentifiers = apiInPackageIdentifiers;
        this.apiOutPackageIdentifiers = apiOutPackageIdentifiers;
        this.applicationPackageIdentifiers = applicationPackageIdentifiers;
        this.adapterInPackageIdentifiers = adapterInPackageIdentifiers;
        this.adapterOutPackageIdentifiers = adapterOutPackageIdentifiers;
        this.externalAdapterOutPackageIdentifiers = externalAdapterOutPackageIdentifiers;
        this.configurationPackageIdentifiers = configurationPackageIdentifiers;
        this.ignoredDependencies = ignoredDependencies;
        this.overriddenDescription = overriddenDescription;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture apiIn(String... packageIdentifiers) {
        apiInPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture apiOut(String... packageIdentifiers) {
        apiOutPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture domainModels(String... packageIdentifiers) {
        domainModelPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture applicationServices(String... packageIdentifiers) {
        applicationPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture adapterIn(String... packageIdentifiers) {
        adapterInPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture adapterOut(String... packageIdentifiers) {
        adapterOutPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture externalAdapterOut(String... packageIdentifiers) {
        externalAdapterOutPackageIdentifiers = packageIdentifiers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture configuration(String... packageIdentifiers) {
        configurationPackageIdentifiers = packageIdentifiers;
        return this;
    }

    /**
     * @param optionalLayers Whether the different parts of the Hex Architecture (domain models, domain services, ...) should be allowed to be empty.
     *                       If set to {@code false} the {@link HexArchitecture HexArchitecture} will fail if any such layer does not contain any class.
     */
    @PublicAPI(usage = ACCESS)
    public HexArchitecture withOptionalLayers(boolean optionalLayers) {
        this.optionalLayers = optionalLayers;
        return this;
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture ignoreDependency(Class<?> origin, Class<?> target) {
        return ignoreDependency(equivalentTo(origin), equivalentTo(target));
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture ignoreDependency(String origin, String target) {
        return ignoreDependency(name(origin), name(target));
    }

    @PublicAPI(usage = ACCESS)
    public HexArchitecture ignoreDependency(DescribedPredicate<? super JavaClass> origin, DescribedPredicate<? super JavaClass> target) {
        this.ignoredDependencies.add(new HexArchitecture.IgnoredDependency(origin, target));
        return this;
    }

    private Architectures.LayeredArchitecture layeredArchitectureDelegate() {
        Architectures.LayeredArchitecture layeredArchitectureDelegate = Architectures.layeredArchitecture()
            .layer(DOMAIN_MODEL_LAYER).definedBy(domainModelPackageIdentifiers)
            .layer(API_IN_LAYER).definedBy(apiInPackageIdentifiers)
            .layer(API_OUT_LAYER).definedBy(apiOutPackageIdentifiers)
            .layer(APPLICATION_SERVICE_LAYER).definedBy(applicationPackageIdentifiers)
            .layer(ADAPTER_IN_LAYER).definedBy(adapterInPackageIdentifiers)
            .layer(ADAPTER_OUT_LAYER).definedBy(adapterOutPackageIdentifiers)
            .layer(EXTERNAL_ADAPTER_OUT_LAYER).definedBy(externalAdapterOutPackageIdentifiers)
            .layer(CONFIGURATION_LAYER).definedBy(configurationPackageIdentifiers)
            .whereLayer(DOMAIN_MODEL_LAYER).mayOnlyBeAccessedByLayers(API_IN_LAYER, API_OUT_LAYER, APPLICATION_SERVICE_LAYER, ADAPTER_IN_LAYER, ADAPTER_OUT_LAYER, CONFIGURATION_LAYER)
            .whereLayer(API_IN_LAYER).mayOnlyBeAccessedByLayers(APPLICATION_SERVICE_LAYER, ADAPTER_IN_LAYER, EXTERNAL_ADAPTER_OUT_LAYER, CONFIGURATION_LAYER)
            .whereLayer(API_OUT_LAYER).mayOnlyBeAccessedByLayers(APPLICATION_SERVICE_LAYER, ADAPTER_OUT_LAYER, CONFIGURATION_LAYER)
            .whereLayer(APPLICATION_SERVICE_LAYER).mayOnlyBeAccessedByLayers(CONFIGURATION_LAYER)
            .whereLayer(ADAPTER_IN_LAYER).mayOnlyBeAccessedByLayers(CONFIGURATION_LAYER)
            .whereLayer(ADAPTER_OUT_LAYER).mayOnlyBeAccessedByLayers(CONFIGURATION_LAYER)
            .withOptionalLayers(optionalLayers);

        for (HexArchitecture.IgnoredDependency ignoredDependency : this.ignoredDependencies) {
            layeredArchitectureDelegate = ignoredDependency.ignoreFor(layeredArchitectureDelegate);
        }
        return layeredArchitectureDelegate.as(getDescription());
    }

    private String[] concatenateAll(Collection<String[]> arrays) {
        List<String> resultList = new ArrayList<>();
        for (String[] array : arrays) {
            resultList.addAll(Arrays.asList(array));
        }
        return resultList.toArray(new String[0]);
    }

    @Override
    public void check(JavaClasses classes) {
        layeredArchitectureDelegate().check(classes);
    }

    @Override
    public ArchRule because(String reason) {
        return ArchRule.Factory.withBecause(this, reason);
    }

    /**
     * This method is equivalent to calling {@link #withOptionalLayers(boolean)}, which should be preferred in this context
     * as the meaning is easier to understand.
     */
    @Override
    public ArchRule allowEmptyShould(boolean allowEmptyShould) {
        return withOptionalLayers(allowEmptyShould);
    }

    @Override
    public HexArchitecture as(String newDescription) {
        return new HexArchitecture(
            domainModelPackageIdentifiers,
            apiInPackageIdentifiers,
            apiOutPackageIdentifiers,
            applicationPackageIdentifiers,
            adapterInPackageIdentifiers,
            adapterOutPackageIdentifiers,
            externalAdapterOutPackageIdentifiers,
            configurationPackageIdentifiers,
            ignoredDependencies,
            Optional.of(newDescription));
    }

    @Override
    public EvaluationResult evaluate(JavaClasses classes) {
        return layeredArchitectureDelegate().evaluate(classes);
    }

    @Override
    public String getDescription() {
        if (overriddenDescription.isPresent()) {
            return overriddenDescription.get();
        }

        List<String> lines = newArrayList("Hex architecture consisting of" + (optionalLayers ? " (optional)" : ""));
        if (domainModelPackageIdentifiers.length > 0) {
            lines.add(String.format("domain models ('%s')", Joiner.on("', '").join(domainModelPackageIdentifiers)));
        }
        if (apiInPackageIdentifiers.length > 0) {
            lines.add(String.format("api in ('%s')", Joiner.on("', '").join(apiInPackageIdentifiers)));
        }
        if (apiOutPackageIdentifiers.length > 0) {
            lines.add(String.format("api out ('%s')", Joiner.on("', '").join(apiOutPackageIdentifiers)));
        }
        if (applicationPackageIdentifiers.length > 0) {
            lines.add(String.format("application services ('%s')", Joiner.on("', '").join(applicationPackageIdentifiers)));
        }
        if (adapterInPackageIdentifiers.length > 0) {
            lines.add(String.format("adapter in ('%s')", Joiner.on("', '").join(adapterInPackageIdentifiers)));
        }
        if (adapterOutPackageIdentifiers.length > 0) {
            lines.add(String.format("adapter out ('%s')", Joiner.on("', '").join(adapterOutPackageIdentifiers)));
        }
        if (externalAdapterOutPackageIdentifiers.length > 0) {
            lines.add(String.format("external adapter out ('%s')", Joiner.on("', '").join(externalAdapterOutPackageIdentifiers)));
        }
        if (configurationPackageIdentifiers.length > 0) {
            lines.add(String.format("configuration ('%s')", Joiner.on("', '").join(configurationPackageIdentifiers)));
        }
        return Joiner.on(lineSeparator()).join(lines);
    }

    private static class IgnoredDependency {
        private final DescribedPredicate<? super JavaClass> origin;
        private final DescribedPredicate<? super JavaClass> target;

        IgnoredDependency(DescribedPredicate<? super JavaClass> origin, DescribedPredicate<? super JavaClass> target) {
            this.origin = origin;
            this.target = target;
        }

        Architectures.LayeredArchitecture ignoreFor(Architectures.LayeredArchitecture layeredArchitecture) {
            return layeredArchitecture.ignoreDependency(origin, target);
        }
    }
}