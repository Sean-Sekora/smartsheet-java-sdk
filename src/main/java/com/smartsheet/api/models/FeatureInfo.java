package com.smartsheet.api.models;
/*
 * #[license]
 * Smartsheet SDK for Java
 * %%
 * Copyright (C) 2014 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

public class FeatureInfo {
    /**
     * Represents the symbols version
     */
    private Integer symbolsVersion;

    /**
     * Gets the symbols version.
     *
     * @return the symbols version
     */
    public Integer getSymbolsVersion() {
        return symbolsVersion;
    }

    /**
     * Sets the symbols version.
     *
     * @param symbolsVersion the symbols version
     */
    public void setSymbolsVersion(Integer symbolsVersion) {
        this.symbolsVersion = symbolsVersion;
    }
}
