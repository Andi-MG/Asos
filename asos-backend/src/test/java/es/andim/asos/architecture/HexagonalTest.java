package es.andim.asos.architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

@AnalyzeClasses(packages = "es.andim.asos", importOptions = ImportOption.DoNotIncludeTests.class)
class HexagonalTest {

    @ArchTest
    static final ArchRule businessLogicShouldBeIndependentOfAdapters = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("domain").definedBy("..domain..")
            .layer("application").definedBy("..application..")
            .layer("infrastructure").definedBy("..infrastructure..")
            .whereLayer("domain").mayNotAccessAnyLayer()
            .whereLayer("application").mayOnlyAccessLayers("domain")
            .whereLayer("infrastructure").mayNotBeAccessedByAnyLayer()
            .as("Business logic should be independent of any adapter")
            .because("it decouples our business needs with the rest of the system");

    @ArchTest
    static final ArchRule noClassesShouldUseFieldInjection = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule shouldNotUseJavaUtilLoginOrGenericExceptions = CompositeArchRule
            .of(GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING)
            .and(GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS);



    @ArchTest
    static final ArchRule adaptersShouldBeIndependentOfEachOther = slices()
            .matching("..infrastructure.(in|out).(*)..")
            .should()
            .notDependOnEachOther();

}
