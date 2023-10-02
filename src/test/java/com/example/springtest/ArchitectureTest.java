package com.example.springtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public class ArchitectureTest {

  JavaClasses javaClasses;

  @BeforeEach
  void beforeEach() {
    javaClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("com.example.springtest");
  }

  @Test
  @DisplayName("controller 패키지 안에 있는 클래스들은 Controller로 끝나야 합니다.")
  void controllerTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .haveSimpleNameEndingWith("Api");

    ArchRule annotationRule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .beAnnotatedWith(RestController.class)
            .orShould()
            .beAnnotatedWith(Controller.class);

    rule.check(javaClasses);
    annotationRule.check(javaClasses);
  }

  @Test
  @DisplayName("request 패키지 안에 있는 클래스는 Request로 끝내야 합니다.")
  void requestTests() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..request..")
            .should()
            .haveSimpleNameEndingWith("Request");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("response 패키지 안에 있는 클래스는 Response로 끝나야 합니다.")
  void responseTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..response..")
            .should()
            .haveSimpleNameEndingWith("Response");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("infra 패키지 안에 있는 클래스는 Repository로 끝나야 합니다.")
  void repositoryTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..infra..")
            .should()
            .haveSimpleNameEndingWith("Repository")
            .andShould()
            .beInterfaces();

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("service 패키지 안에 클래스는 Service로 끝나야 하고, @Service 어노테이션이 붙여있어야 합니다.")
  void serviceTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..application..")
            .should()
            .haveSimpleNameEndingWith("Service")
            .andShould()
            .beAnnotatedWith(Service.class);

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("config 패키지 안에 있는 클래스는 Config로 끝나냐하고, @Configuration 어노테이션이 붙어있어야 합니다.")
  void configTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..config..")
            .should()
            .haveSimpleNameEndingWith("Config")
            .andShould()
            .beAnnotatedWith(Configuration.class);

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Controller 는 Service와 Request/Response를 사용할 수 있음")
  void controllerDependencyTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..request..", "..response..", "..service..");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Controller는 의존되지 않음")
  void controllerDependencyTest2() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..controller");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("controller는 도메인을 사용할 수 없음")
  void controllerDependencyTest3() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..domain..");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Service는 Controller를 의존하면 안됨")
  void serviceDependencyTest() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAnyPackage("..application")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..controller");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("도메인은 오직 Service와 Repository에 의해 의존됨")
  void modelDependencyTest() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..domain..")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..infra..", "..application..", "..domain..", "..calculator");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("도메인은 아무것도 의존하지 않음")
  void modelDependencryTest2() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..domain..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..domain..", "java..", "jakarta..", "..exception");

    rule.check(javaClasses);
  }
}
