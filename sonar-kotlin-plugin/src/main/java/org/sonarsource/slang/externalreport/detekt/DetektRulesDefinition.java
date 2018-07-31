/*
 * SonarSource SLang
 * Copyright (C) 2009-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.slang.externalreport.detekt;

import org.sonarsource.slang.kotlin.KotlinPlugin;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.ExternalRuleLoader;

public class DetektRulesDefinition implements RulesDefinition {

  static final ExternalRuleLoader RULE_LOADER = new ExternalRuleLoader(
    DetektSensor.LINTER_KEY,
    DetektSensor.LINTER_NAME,
    "org/sonar/l10n/kotlin/rules/detekt/rules.json",
    KotlinPlugin.KOTLIN_LANGUAGE_KEY);

  private final boolean externalIssuesSupported;

  public DetektRulesDefinition(boolean externalIssuesSupported) {
    this.externalIssuesSupported = externalIssuesSupported;
  }

  @Override
  public void define(Context context) {
    if (externalIssuesSupported) {
      RULE_LOADER.createExternalRuleRepository(context);
    }
  }

}