
package com.ashish.custom.sonar.java.plugin;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import com.ashish.custom.sonar.java.rules.AvoidSmallerLengthVariableNameRule;

/**
 * Provide the "checks" (implementations of rules) classes that are going to get executed during the source code analysis.
 *
 * This class is a batch extension by implementing the {@link org.sonar.plugins.java.api.CheckRegistrar} interface.
 */
public class CustomJavaFileCheckRegistrar implements CheckRegistrar {

  /**
   * Register the classes that will be used to instantiate checks during analysis.
   */
  @Override
  public void register(RegistrarContext registrarContext) {
    // Call to registerClassesForRepository to associate the classes with the correct repository key
    registrarContext.registerClassesForRepository(CustomRulesDefinition.REPOSITORY_KEY, Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
  }

  /**
   * Lists all the checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] checkClasses() {
    return new Class[] { // List of rules to be included here
      AvoidSmallerLengthVariableNameRule.class
    };
  }

  /**
   * Lists all the test checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] testCheckClasses() {
    return new Class[] {};
  }
}