/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.driver.state;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.state.StateType;
import org.apache.shardingsphere.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.spi.type.typed.TypedSPIRegistry;

/**
 * Driver state factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverStateFactory {
    
    static {
        ShardingSphereServiceLoader.register(DriverState.class);
    }
    
    /**
     * Create new instance of driver state.
     * 
     * @param type state type 
     * @return new instance of driver state
     */
    public static DriverState newInstance(final StateType type) {
        return TypedSPIRegistry.getRegisteredService(DriverState.class, type.name());
    }
}
